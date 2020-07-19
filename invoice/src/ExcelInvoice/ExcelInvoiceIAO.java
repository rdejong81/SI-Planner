/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ExcelInvoice;

import Timeregistration.Invoice;
import Timeregistration.InvoiceIAO;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com4j.COM4J;
import com4j.Com4jObject;
import com4j.NativeType;
import excel.*;
import com4j.Variant;

public class ExcelInvoiceIAO implements InvoiceIAO
{
    private byte work[];
    private FileOutputStream fileOutputStream;
    private _Application app;
    private _Workbook workbook;
    private File tempFile;
    private final HashMap<String, Map.Entry<Integer, Integer>> stringRangeMap;
    static final String PARAMETER_PATTERN = "@@([A-Za-z\\s]+[0-9]{0,3})@@";
    private final Pattern pattern = Pattern.compile(PARAMETER_PATTERN);
    private boolean started;
    private excel._Worksheet templateSheet, activeSheet;
    private Invoice invoice;

    public ExcelInvoiceIAO()
    {
        COM4J.addShutdownTask(() ->
        {
            stop();
        });
        stringRangeMap = new HashMap<>();
        started = false;
    }

    @Override
    public void start(Invoice invoice, String pageName)
    {
        if (!started)
        {
            this.invoice = invoice;

            app = excel.ClassFactory.createApplication();
            app.setDisplayAlerts(0, false);

            work = invoice.getDocumentTemplate().getData();
            try
            {
                tempFile = File.createTempFile("siplanner", ".xlsx");
                tempFile.deleteOnExit();
                fileOutputStream = new FileOutputStream(tempFile);
                fileOutputStream.write(work);
                fileOutputStream.close();
                workbook = app.getWorkbooks().open(tempFile.getAbsolutePath(), null, null,
                        null, null, null, true, XlPlatform.xlWindows
                        , null,
                        null, null,
                        com4j.Variant.getMissing(), false, true,
                        XlCorruptLoad.xlNormalLoad, 1033);
                app.setVisible(0, true);

                for (Com4jObject childWorksheet : workbook.getWorksheets())
                {
                    templateSheet = childWorksheet.queryInterface(_Worksheet.class);
                    activeSheet = templateSheet;
                    break;
                }
                started = true;

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        templateSheet.copy(null, activeSheet, 0);
        for (Com4jObject childWorksheet : workbook.getWorksheets())
        {
            activeSheet = childWorksheet.queryInterface(_Worksheet.class);
        }

        activeSheet.setName(pageName);

        //app.getWorkbooks().add(null, 0);
        //app.getActiveCell().setFormulaR1C1("Welcome to com4j");

    }

    @Override
    public void stop()
    {
        if (activeSheet != null && templateSheet != null)
        {
            activeSheet = null;
            templateSheet = null;
            app.setDisplayAlerts(0, true);
        }
        stringRangeMap.clear();
        if (workbook != null)
            workbook.close(0, null, null, 0);
        if (app != null && app.getWorkbooks().getCount() == 0)
            app.quit(); // quit if nothing else opened.
        if (tempFile != null)
            tempFile.delete();

        workbook = null;
        app = null;
        started = false;
    }

    private Range findLastCell()
    {
        Range used = activeSheet.getUsedRange(0).queryInterface(Range.class);
        return used.specialCells(XlCellType.xlCellTypeLastCell,com4j.Variant.getMissing()).queryInterface(Range.class);
    }

    @Override
    public List<String> findParameters()
    {
        if (workbook == null) return null;
        if (!stringRangeMap.isEmpty())
            return Collections.unmodifiableList(List.copyOf(stringRangeMap.keySet()));

        ArrayList<String> parameters = new ArrayList<>();

        Range cells = activeSheet.getUsedRange(0).queryInterface(Range.class);
        Range lastCell = findLastCell();
        int rows = lastCell.getRow();
        int cols = lastCell.getColumn();


        for (int row = 1; row - 1 < rows; row++)
        {
            for (int col = 1; col - 1 < cols; col++)
            {
                Com4jObject cell = (Com4jObject) cells.getItem(row, col);
                Range cellObj = cell.queryInterface(Range.class);
                Object value = cellObj.getFormula();
                if (value != null && !value.toString().isEmpty())
                {
                    //Test parameter pattern
                    Matcher m = pattern.matcher(value.toString());
                    while (m.find())
                    {
                        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleImmutableEntry<>(row, col);
                        stringRangeMap.put(String.copyValueOf(m.group(1).toCharArray()), entry);
                        parameters.add(String.copyValueOf(m.group(1).toCharArray()));
                    }

                }
            }

        }


        return parameters;
    }

    /**
     * Convert Date to Microsoft OLE Automation - OADate type
     * @param date
     * @return double expected by OLE Variant type
     * @throws ParseException error in parsing date
     */
    public static double convertToOADate(Date date) throws ParseException
    {
        double oaDate;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date baseDate = myFormat.parse("30 12 1899");
        Long days = TimeUnit.DAYS.convert(date.getTime() - baseDate.getTime(), TimeUnit.MILLISECONDS);

        oaDate = (double) days + ((double) date.getHours() / 24) + ((double) date.getMinutes() / (60 * 24)) + ((double) date.getSeconds() / (60 * 24 * 60));
        return oaDate;
    }

    @Override
    public void fillParameter(String name, Object value)
    {
        if (workbook == null || !stringRangeMap.containsKey(name)) return;
        Map.Entry<Integer, Integer> integerEntry = stringRangeMap.get(name);

        Com4jObject cell = (Com4jObject) activeSheet.getUsedRange(0).queryInterface(Range.class).getItem(integerEntry.getKey(), integerEntry.getValue());
        Range cellObj = cell.queryInterface(Range.class);

        Variant variant = new Variant();
        if(value instanceof LocalDate)
        {
            try
            {
                //default time zone
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date dateObj = Date.from(((LocalDate)value).plusDays(1).atStartOfDay(defaultZoneId).toInstant());

                variant.set(convertToOADate(dateObj));
                variant.setType(Variant.Type.VT_DATE);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }


        } else if(value instanceof Double) {
            variant.set((Double)value);
        } else if(value instanceof String)
        {
            // support partial replacing, to support multiple text parameters in cell
            String currentValue = (String)cellObj.getValue(com4j.Variant.getMissing());
            variant.set(currentValue.replace("@@"+name+"@@",(String)value));
        } else {
            variant.set(value.toString());
        }


        try
        {
            cellObj.setValue(10, variant);
        } catch (com4j.ComException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public byte[] retrieve()
    {
        if (templateSheet == null || workbook == null) return null;
        templateSheet.delete(0);
        workbook.save(0);
        try
        {
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            work = fileInputStream.readAllBytes();
            fileInputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        stop();
        return work;
    }

    @Override
    public String supportedFileExtension()
    {
        return "xlsx";
    }
}

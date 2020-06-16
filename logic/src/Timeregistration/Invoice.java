package Timeregistration;

import Data.Attribute;
import Data.Customer;
import Data.DocumentTemplate;
import Data.Employee;
import Facade.AppFacade;
import Projects.Project;
import Projects.ProjectTask;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Invoice
{
    private final DocumentTemplate documentTemplate;
    private final Employee employee;
    private final Customer customer;
    private final InvoiceIAO invoiceIAO;
    private byte work[]=null;

    public Invoice(DocumentTemplate documentTemplate, Employee employee, Customer customer, InvoiceIAO invoiceIAO)
    {
        this.documentTemplate = documentTemplate;
        this.employee = employee;
        this.invoiceIAO = invoiceIAO;
        this.customer = customer;

    }

    public void generate(LocalDate start, LocalDate end)
    {
        if(work != null) return;
        boolean empty = true;
        boolean canContinue = false;
        for(Project project : customer.getProjects())
        {
            boolean projectEmpty = true;
            if(!project.isInvoice() || project.getProjectTasks().isEmpty()) continue;

            ArrayList<String> parametersUnfilled = new ArrayList<>();
            List<LocalDate> dates = Arrays.asList(start.datesUntil(end.plusDays(1)).toArray(LocalDate[]::new));

            HashMap<String,String> parameterTasks = new HashMap<>(); // summary of tasks spent per day parameter.
            HashMap<String,Double> parameterTimeTotal = new HashMap<>(); // summary of total project time per day parameter.
            HashMap<String,String> parameterAttributeJoin = new HashMap<>(); //joined attribute values for day parameter.

            //check to see if sheet generation is needed.
            for (ProjectTask projectTask : project.getProjectTasks())
                for (Timeregistration timeregistration : projectTask.getTimeregistrations())
                    if (timeregistration.getStart().isAfter(start.atStartOfDay())
                    && timeregistration.getStart().isBefore(end.atStartOfDay()))
                        canContinue = true;

            if(canContinue)
            for(int datenr=1;datenr-1<dates.size();datenr++)
            {
                AppFacade.appFacade.broadcastStatusUpdate(
                        String.format("Generating timesheet for project %s",project.getName()),
                        datenr,dates.size());
                for (ProjectTask projectTask : project.getProjectTasks())
                    for (Timeregistration timeregistration : projectTask.getTimeregistrations())
                    {
                        // only timeregistrations for intended employee
                        if (timeregistration.getEmployee() != employee) continue;

                        if (projectEmpty)
                        {
                            invoiceIAO.start(this, project.getShortName());
                            parametersUnfilled = new ArrayList<>(invoiceIAO.findParameters());
                        }

                        projectEmpty = false;
                        empty = false;
                        for (String parameter : invoiceIAO.findParameters())
                        {
                            // if already filled, skip
                            if (!parametersUnfilled.contains(parameter)) continue;

                            Pattern pattern = Pattern.compile("([A-Za-z\\s]+)([0-9]{0,3})");
                            Matcher matcher = pattern.matcher(parameter);
                            String namePart = matcher.find() && !matcher.group(1).isEmpty() ? matcher.group(1) : "";
                            Integer dayPart = !matcher.group(1).isEmpty() && !matcher.group(2).isEmpty() ? Integer.parseInt(matcher.group(2)) : 0;
                            // if not matching day to check, skip
                            if (dayPart != 0 && dayPart != datenr) continue;

                            switch (namePart)
                            {
                                case "DATE":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    invoiceIAO.fillParameter(parameter, dates.get(dayPart - 1));
                                    parametersUnfilled.remove(parameter);
                                    break;
                                case "DAYOFMONTH":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    invoiceIAO.fillParameter(parameter, String.valueOf(dates.get(dayPart - 1).getDayOfMonth()));
                                    parametersUnfilled.remove(parameter);
                                    break;
                                case "DAYSHORTNL":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    invoiceIAO.fillParameter(parameter,
                                            dates.get(dayPart - 1).getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("nl", "NL"))
                                                    .substring(0, 2).toUpperCase()
                                    );
                                    parametersUnfilled.remove(parameter);
                                    break;
                                case "DAYSHORT":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    invoiceIAO.fillParameter(parameter,
                                            dates.get(dayPart - 1).getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("en", "US"))
                                    );
                                    parametersUnfilled.remove(parameter);
                                    break;
                                case "DAYLONG":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    invoiceIAO.fillParameter(parameter,
                                            dates.get(dayPart - 1).getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("en", "US"))
                                    );
                                    parametersUnfilled.remove(parameter);
                                    break;
                                case "EMPLOYEE":
                                    invoiceIAO.fillParameter(parameter,
                                            employee.getName()
                                    );
                                    parametersUnfilled.remove(parameter);
                                    break;
                                case "TASK":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    // only timeregistrations for intended for the date to process
                                    if (!timeregistration.getStart().toLocalDate().equals(dates.get(datenr - 1)))
                                        break;
                                    // first task for this day?
                                    if (!parameterTasks.containsKey(parameter))
                                    {
                                        parameterTasks.put(parameter, timeregistration.getProjectTask().getName());
                                    } else
                                    {
                                        String currentTask = parameterTasks.get(parameter);
                                        parameterTasks.replace(parameter, currentTask + "," + timeregistration.getProjectTask().getName());
                                    }
                                    break;
                                case "TIME":
                                    if (dayPart < 1 || dayPart > dates.size()) break;
                                    // only timeregistrations for intended for the date to process
                                    if (!timeregistration.getStart().toLocalDate().equals(dates.get(datenr - 1)))
                                        break;
                                    double minutes = ChronoUnit.MINUTES.between(timeregistration.getStart(), timeregistration.getEnd());
                                    double hours = minutes / 60.0;
                                    // first task for this day?
                                    if (!parameterTimeTotal.containsKey(parameter))
                                    {
                                        parameterTimeTotal.put(parameter, hours);
                                    } else
                                    {
                                        double newHours = parameterTimeTotal.get(parameter) + hours;
                                        parameterTimeTotal.replace(parameter, newHours);
                                    }
                                    break;
                                default:
                                    //handle time attributes
                                    for (Attribute attribute : timeregistration.getAttributes())
                                    {
                                        if (namePart.equalsIgnoreCase(attribute.getName()) &&
                                                timeregistration.getStart().toLocalDate().equals(dates.get(datenr - 1)))
                                        {
                                            if (!parameterAttributeJoin.containsKey(parameter))
                                            {
                                                parameterAttributeJoin.put(parameter, attribute.getValue().toString());
                                            } else
                                            {
                                                String newValue = parameterAttributeJoin.get(parameter) + "," +
                                                        attribute.getValue().toString();
                                                parameterAttributeJoin.replace(parameter, newValue);
                                            }
                                            break;
                                        }
                                    }
                                    // handle employee attributes
                                    for (Attribute attribute : employee.getAttributes())
                                    {
                                        if (namePart.equalsIgnoreCase(attribute.getName()))
                                        {
                                            invoiceIAO.fillParameter(parameter, attribute.getValue());
                                            parametersUnfilled.remove(parameter);
                                        }
                                    }
                                    // handle project attributes
                                    for (Attribute attribute : project.getAttributes())
                                    {
                                        if (namePart.equalsIgnoreCase(attribute.getName()))
                                        {
                                            invoiceIAO.fillParameter(parameter, attribute.getValue());
                                            parametersUnfilled.remove(parameter);
                                        }
                                    }
                                    // handle customer attributes
                                    for (Attribute attribute : customer.getAttributes())
                                    {
                                        if (namePart.equalsIgnoreCase(attribute.getName()))
                                        {
                                            invoiceIAO.fillParameter(parameter, attribute.getValue());
                                            parametersUnfilled.remove(parameter);
                                        }
                                    }

                            }


                        }



                    }

                // task descriptions
                for (Map.Entry<String, String> parameterTask : parameterTasks.entrySet())
                {

                    invoiceIAO.fillParameter(parameterTask.getKey(),
                            parameterTask.getValue()
                    );
                    parametersUnfilled.remove(parameterTask.getKey());
                }

                // time totals
                for (Map.Entry<String, Double> parameterTime : parameterTimeTotal.entrySet())
                {

                    invoiceIAO.fillParameter(parameterTime.getKey(),
                            parameterTime.getValue()
                    );
                    parametersUnfilled.remove(parameterTime.getKey());
                }

                // attribute joins
                for (Map.Entry<String, String> attributeJoin : parameterAttributeJoin.entrySet())
                {
                    invoiceIAO.fillParameter(attributeJoin.getKey(),
                            attributeJoin.getValue()
                    );
                    parametersUnfilled.remove(attributeJoin.getKey());
                }

            }

            //unhandled parameters
            for(String parameter : parametersUnfilled)
            {
                invoiceIAO.fillParameter(parameter, "");
            }

        }



        work = empty ? null : invoiceIAO.retrieve();
        if(empty)
            AppFacade.appFacade.broadcastStatusUpdate(
                    String.format("No time registrations found to invoice between %s and %s for employee %s.",start.toString(),end.toString(),employee.getName()),0,0);
        else
            AppFacade.appFacade.broadcastStatusUpdate("Completed invoices.",0,0);

        invoiceIAO.stop();
    }

    public DocumentTemplate getDocumentTemplate()
    {
        return documentTemplate;
    }

    public byte[] getDocument()
    {
        return work;
    }

}

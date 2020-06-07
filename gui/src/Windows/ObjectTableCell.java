
package Windows;

import impl.com.calendarfx.view.NumericTextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.time.LocalDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ObjectTableCell<S> extends TableCell<S,Object>
{
    private final CheckBox checkBox;
    private final DatePicker datePicker;
    private final TextField textField,doubleField;
    private final NumericTextField numericTextField;
    private final Pattern validDoubleEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
    private final UnaryOperator<TextFormatter.Change> doubleFilter = c -> {
        String text = c.getControlNewText();
        if (validDoubleEditingState.matcher(text).matches()) {
            return c ;
        } else {
            return null ;
        }
    };
    private final StringConverter<Double> doubleConverter = new StringConverter<Double>() {

        @Override
        public Double fromString(String s) {
            if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                return 0.0 ;
            } else {
                return Double.valueOf(s);
            }
        }


        @Override
        public String toString(Double d) {
            return d.toString();
        }
    };

    private TextFormatter<Double> doubleFormatter = new TextFormatter<>(doubleConverter, 0.0, doubleFilter);



    public static <S> Callback<TableColumn<S,Object>, TableCell<S,Object>> forTableColumn() {
        return list -> {
            return new ObjectTableCell<>();
        };
    }

    private ObjectTableCell()
    {
        numericTextField = new NumericTextField();
        numericTextField.setOnAction(event -> {
            if(isEditing())
            {
                this.commitEdit((Object)Integer.valueOf(numericTextField.getText()));
                event.consume();
            }
        });

        doubleField = new TextField();
        doubleField.setTextFormatter(doubleFormatter);
        doubleField.setOnAction(event -> {
        if(isEditing())
        {
            this.commitEdit((Object)doubleFormatter.getValue());
            event.consume();
        }
    });

        textField = new TextField();
        textField.setOnAction(event -> {
            if(isEditing())
            {
                this.commitEdit((Object)textField.getText());
                event.consume();
            }
        });

        datePicker = new DatePicker();
        datePicker.setShowWeekNumbers(true);
        datePicker.setChronology(
                Chronology.ofLocale(
                        Locale.UK
                        ));
        datePicker.setOnAction(event -> {
            if(isEditing())
            {
                this.commitEdit((Object)datePicker.getValue().atStartOfDay());
                event.consume();
            }
        });


        checkBox = new CheckBox();
        checkBox.setOnAction(event ->
        {
            if(isEditing())
            {
                this.commitEdit((Object)checkBox.isSelected());
                event.consume();
            }
        });

    }

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            if (item instanceof String) {
                setText((String) item);
                textField.setText((String) item);
                setGraphic(null);
            } else if (item instanceof Integer) {
                setText(Integer.toString((Integer) item));
                numericTextField.setText(((Integer) item).toString());
                setGraphic(null);
            } else if (item instanceof Boolean) {
                checkBox.setSelected((boolean) item);
                checkBox.setDisable(!isEditing());
                setGraphic(checkBox);
            } else if (item instanceof LocalDateTime)
            {
                setText(((LocalDateTime) item).toLocalDate().format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                datePicker.setValue(((LocalDateTime) item).toLocalDate());
                setGraphic(null);
            } else if (item instanceof Double) {
                setText(Double.toString((Double) item));
                doubleFormatter.setValue((Double) item);
                setGraphic(null);
            } else {
                setText("N/A");
                setGraphic(null);
            }
        } else {
            setText(null);
            setGraphic(null);
        }
    }

    @Override
    public void startEdit() {
        if (! isEditable()
                || ! getTableView().isEditable()
                || ! getTableColumn().isEditable()) {
            return;
        }
        super.startEdit();

        if (isEditing())
        {
            if(this.getItem() instanceof LocalDateTime)
            {
                setText(null);
                setGraphic(datePicker);
            }
            if(this.getItem() instanceof Boolean)
                checkBox.setDisable(false);
            if(this.getItem() instanceof String)
            {
                setText(null);
                setGraphic(textField);
            }
            if(this.getItem() instanceof Integer)
            {
                setText(null);
                setGraphic(numericTextField);
            }
            if(this.getItem() instanceof Double)
            {
                setText(null);
                setGraphic(doubleField);
            }
        }
    }

    @Override public void cancelEdit()
    {
        super.cancelEdit();
        updateItem(getItem(),false);
    }

}

package calendar;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;

public class DatePickerExpanded extends StackPane
{
    DatePickerSkin datePickerSkin;
    DatePicker datePicker;
    ObjectProperty<LocalDate> selectedValue;

    public DatePickerExpanded()
    {
        datePicker = new DatePicker(LocalDate.now());
        datePicker.setShowWeekNumbers(true);
        datePicker.setPickOnBounds(true);
        selectedValue = datePicker.valueProperty();
        datePickerSkin = new DatePickerSkin(datePicker);


    this.getChildren().add(datePickerSkin.getPopupContent());
    }

    public ObjectProperty<LocalDate> getSelectedValue(){
        return selectedValue;
    }
}

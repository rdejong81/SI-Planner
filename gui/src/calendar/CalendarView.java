package calendar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarView extends GridPane
{

    private final LocalTime firstSlotStart = LocalTime.of(0, 0);
    private final Duration slotLength = Duration.ofMinutes(60);
    private final LocalTime lastSlotStart = LocalTime.of(23, 59);

    private ArrayList<CalendarTime> calenderTimes;

    public CalendarView(){
        super();
        calenderTimes = new ArrayList<>();
        getStylesheets().add(getClass().getResource("calendar.css").toExternalForm());

        render(LocalDate.now());

    }

    public void setDate(LocalDate date)
    {
        render(date);
    }

    private void render(LocalDate today){
        calenderTimes.clear();
        this.getChildren().clear();

        boolean first=true;
        ObjectProperty<CalendarTime> mouseAnchor = new SimpleObjectProperty<>();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1) ;
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        Integer counter=0;


        for (LocalDate date = startOfWeek; ! date.isAfter(endOfWeek); date = date.plusDays(1))
        {
            int slotIndex = 1;

            for (LocalDateTime startTime = date.atTime(firstSlotStart);
                 !startTime.isAfter(date.atTime(lastSlotStart));
                 startTime = startTime.plus(slotLength))
            {



                if(true)
                {
                    CalendarTime timeSlot = new CalendarTime(startTime, slotLength,counter.toString());
                    counter++;
                    calenderTimes.add(timeSlot);

                    registerDragHandlers(timeSlot, mouseAnchor);
                    if(counter != 91)
                    this.add(timeSlot.getView(), timeSlot.getDayOfWeek().getValue(), slotIndex);
                    if(counter == 90 ){
                        this.setRowSpan(timeSlot.getView(),2);
                    }
                }



                slotIndex++;
            }


        }

        //headers:

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E\nMMM d");
        Label flabel = new Label("time");
        Label label = null;
        this.getStyleClass().add("time-header");
        flabel.setPadding(new Insets(1));
        flabel.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(flabel, HPos.CENTER);
        this.add(flabel,0,0);


        for (LocalDate date = startOfWeek; ! date.isAfter(endOfWeek); date = date.plusDays(1)) {
            label = new Label(date.format(dayFormatter));
            label.setPadding(new Insets(1));
            label.setTextAlignment(TextAlignment.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            this.add(label, date.getDayOfWeek().getValue(), 0);
        }


        int slotIndex = 1 ;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        for (LocalDateTime startTime = today.atTime(firstSlotStart);
             ! startTime.isAfter(today.atTime(lastSlotStart));
             startTime = startTime.plus(slotLength)) {
            label = new Label(startTime.format(timeFormatter));
            label.setPadding(new Insets(2));
            GridPane.setHalignment(label, HPos.RIGHT);
            this.add(label, 0, slotIndex);
            slotIndex++ ;
        }
        this.setGridLinesVisible(false);
        this.updateBounds();



    }


    // Registers handlers on the time slot to manage selecting a range of slots in the grid.

    private void registerDragHandlers(CalendarTime timeSlot, ObjectProperty<CalendarTime> mouseAnchor) {
        timeSlot.getView().setOnMouseClicked(event -> {
            mouseAnchor.set(timeSlot);
            calenderTimes.forEach(slot ->
                    slot.setSelected(slot == timeSlot));
        });

        timeSlot.getView().setOnMouseEntered(event -> {
            mouseAnchor.set(timeSlot);
            calenderTimes.forEach(slot ->
                    slot.setFilled(slot == timeSlot));
        });


        timeSlot.getView().setOnDragDetected(event -> {
            mouseAnchor.set(timeSlot);
            timeSlot.getView().startFullDrag();
            calenderTimes.forEach(slot ->
                    slot.setSelected(slot == timeSlot));
        });

        timeSlot.getView().setOnMouseDragEntered(event -> {
            CalendarTime startSlot = mouseAnchor.get();
            calenderTimes.forEach(slot ->
                    slot.setSelected(isBetween(slot, startSlot, timeSlot)));
        });

        timeSlot.getView().setOnMouseReleased(event -> mouseAnchor.set(null));
    }

    // Utility method that checks if testSlot is "between" startSlot and endSlot
    // Here "between" means in the visual sense in the grid: i.e. does the time slot
    // lie in the smallest rectangle in the grid containing startSlot and endSlot
    //
    // Note that start slot may be "after" end slot (either in terms of day, or time, or both).

    // The strategy is to test if the day for testSlot is between that for startSlot and endSlot,
    // and to test if the time for testSlot is between that for startSlot and endSlot,
    // and return true if and only if both of those hold.

    // Finally we note that x <= y <= z or z <= y <= x if and only if (y-x)*(z-y) >= 0.

    private boolean isBetween(CalendarTime testSlot, CalendarTime startSlot, CalendarTime endSlot) {

        boolean daysBetween = testSlot.getDayOfWeek().compareTo(startSlot.getDayOfWeek())
                * endSlot.getDayOfWeek().compareTo(testSlot.getDayOfWeek()) >= 0 ;

        boolean timesBetween = testSlot.getTime().compareTo(startSlot.getTime())
                * endSlot.getTime().compareTo(testSlot.getTime()) >= 0 ;

        return daysBetween && timesBetween ;
    }



}

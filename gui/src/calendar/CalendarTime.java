package calendar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalendarTime
{
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private static final PseudoClass FILLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("filled");
    private final LocalDateTime start ;
    private final Duration duration ;
    private final StackPane view ;

    private final BooleanProperty selected = new SimpleBooleanProperty();

    private final BooleanProperty filled = new SimpleBooleanProperty();

    public final BooleanProperty selectedProperty() {
        return selected ;
    }

    public final BooleanProperty filledProperty() {
        return filled ;
    }

    public final boolean isSelected() {
        return selectedProperty().get();
    }

    public final boolean isFilled() {
        return filledProperty().get();
    }

    public final void setSelected(boolean selected) {
        selectedProperty().set(selected);
    }

    public final void setFilled(boolean filled) {
        filledProperty().set(filled);
    }

    public CalendarTime(LocalDateTime start, Duration duration,String text) {
        this.start = start ;
        this.duration = duration ;

       // view = new Region();
        view = new StackPane();
        view.setMinSize(80, 20);
        view.getStyleClass().add("time-slot");

        selectedProperty().addListener((obs, wasSelected, isSelected) ->
                view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));


        filledProperty().addListener((obs, wasFilled, isFilled) ->
                view.pseudoClassStateChanged(FILLED_PSEUDO_CLASS, isFilled));

        view.getChildren().add(new Label(text));



    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalTime getTime() {
        return start.toLocalTime() ;
    }

    public DayOfWeek getDayOfWeek() {
        return start.getDayOfWeek() ;
    }

    public Duration getDuration() {
        return duration ;
    }

    public Node getView() {
        return view;
    }
}

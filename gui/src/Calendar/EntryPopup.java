package Calendar;

import Data.DataEntity;
import Projects.Project;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.Messages;
import com.calendarfx.view.popover.*;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import java.util.Objects;

public class EntryPopup extends PopOverContentPane
{
    private Entry<?> entry;
    private DateControl dateControl;
    private PopOver popOver;

    public EntryPopup(PopOver popOver, DateControl dateControl, Entry<DataEntity> entry, ObservableList<Project> projects,CalendarView calendarView){
        getStylesheets().add(CalendarView.class.getResource("calendar.css").toExternalForm());
        this.popOver = popOver;
        this.dateControl = dateControl;
        this.entry = Objects.requireNonNull(entry);

        EntryDetails details = new EntryDetails(entry,projects,calendarView);

        PopOverTitledPane detailsPane = new PopOverTitledPane(Messages.getString("EntryPopOverContentPane.DETAILS"), //$NON-NLS-1$
                details);


        EntryHeader header = new EntryHeader(entry, dateControl.getCalendars());
        setHeader(header);

        if (Boolean.getBoolean("calendarfx.developer")) {
            EntryPropertiesView properties = new EntryPropertiesView(entry);
            PopOverTitledPane propertiesPane = new PopOverTitledPane("Properties", properties);
            getPanes().addAll(detailsPane, propertiesPane);
        } else {
            getPanes().addAll(detailsPane);
        }

        setExpandedPane(detailsPane);

        InvalidationListener listener = obs -> {
            if (entry.isFullDay() && !popOver.isDetached()) {
                popOver.setDetached(true);
            }
        };

        entry.fullDayProperty().addListener(listener);
        popOver.setOnHidden(evt -> entry.fullDayProperty().removeListener(listener));

        entry.calendarProperty().addListener(it -> {
            if (entry.getCalendar() == null) {
                popOver.hide(Duration.ZERO);
            }
        });

    }
}

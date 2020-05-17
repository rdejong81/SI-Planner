package Calendar;

import Data.DaoResult;
import Data.DataEntity;
import Facade.AppFacade;
import Planning.Planning;
import Projects.Project;
import Projects.ProjectTask;
import Timeregistration.Timeregistration;
import Windows.ProjectColor;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.util.Util;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.Messages;
import com.calendarfx.view.RecurrenceView;
import com.calendarfx.view.TimeField;
import com.calendarfx.view.popover.EntryPopOverPane;
import com.calendarfx.view.popover.RecurrencePopup;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Set;

public class EntryDetails extends EntryPopOverPane
{

    private final Label summaryLabel;
    private final MenuButton recurrenceButton;

    public EntryDetails(Entry<DataEntity> entry, ObservableList<Project> projects, CalendarView calendarView) {
        super();

        getStyleClass().add("entry-details-view");

        Label fullDayLabel = new Label(Messages.getString("EntryDetailsView.FULL_DAY")); //$NON-NLS-1$
        Label startDateLabel = new Label(Messages.getString("EntryDetailsView.FROM")); //$NON-NLS-1$
        Label endDateLabel = new Label(Messages.getString("EntryDetailsView.TO")); //$NON-NLS-1$
        Label recurrentLabel = new Label(Messages.getString("EntryDetailsView.REPEAT")); //$NON-NLS-1$

        summaryLabel = new Label();
        summaryLabel.getStyleClass().add("recurrence-summary-label"); //$NON-NLS-1$
        summaryLabel.setWrapText(true);
        summaryLabel.setMaxWidth(300);

        CheckBox fullDay = new CheckBox();
        fullDay.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        TimeField startTimeField = new TimeField();
        startTimeField.setValue(entry.getStartTime());
        startTimeField.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        TimeField endTimeField = new TimeField();
        endTimeField.setValue(entry.getEndTime());
        endTimeField.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setValue(entry.getStartDate());
        startDatePicker.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setValue(entry.getEndDate());
        endDatePicker.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        entry.intervalProperty().addListener(it -> {
            startTimeField.setValue(entry.getStartTime());
            endTimeField.setValue(entry.getEndTime());
            startDatePicker.setValue(entry.getStartDate());
            endDatePicker.setValue(entry.getEndDate());
        });

        HBox startDateBox = new HBox(10);
        HBox endDateBox = new HBox(10);

        startDateBox.setAlignment(Pos.CENTER_LEFT);
        endDateBox.setAlignment(Pos.CENTER_LEFT);

        startDateBox.getChildren().addAll(startDateLabel, startDatePicker, startTimeField);
        endDateBox.getChildren().addAll(endDateLabel, endDatePicker, endTimeField);

        fullDay.setSelected(entry.isFullDay());
        startDatePicker.setValue(entry.getStartDate());
        endDatePicker.setValue(entry.getEndDate());

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        ObservableList<ZoneId> zoneIds = FXCollections.observableArrayList();
        for (String id : availableZoneIds) {
            ZoneId zoneId = ZoneId.of(id);
            if (!zoneIds.contains(zoneId)) {
                zoneIds.add(zoneId);
            }
        }

        zoneIds.sort(Comparator.comparing(ZoneId::getId));

        Label zoneLabel = new Label(Messages.getString("EntryDetailsView.TIMEZONE")); //$NON-NLS-1$

        ComboBox<ZoneId> zoneBox = new ComboBox<>(zoneIds);
        zoneBox.disableProperty().bind(entry.getCalendar().readOnlyProperty());
        zoneBox.setConverter(new StringConverter<ZoneId>() {

            @Override
            public String toString(ZoneId object) {
                return object.getId();
            }

            @Override
            public ZoneId fromString(String string) {
                return null;
            }
        });
        zoneBox.setValue(entry.getZoneId());

        recurrenceButton = new MenuButton(Messages.getString("EntryDetailsView.MENU_BUTTON_NONE")); //$NON-NLS-1$

        MenuItem none = new MenuItem(Messages.getString("EntryDetailsView.MENU_ITEM_NONE")); //$NON-NLS-1$
        MenuItem everyDay = new MenuItem(Messages.getString("EntryDetailsView.MENU_ITEM_EVERY_DAY")); //$NON-NLS-1$
        MenuItem everyWeek = new MenuItem(Messages.getString("EntryDetailsView.MENU_ITEM_EVERY_WEEK")); //$NON-NLS-1$
        MenuItem everyMonth = new MenuItem(Messages.getString("EntryDetailsView.MENU_ITEM_EVERY_MONTH")); //$NON-NLS-1$
        MenuItem everyYear = new MenuItem(Messages.getString("EntryDetailsView.MENU_ITEM_EVERY_YEAR")); //$NON-NLS-1$
        MenuItem custom = new MenuItem(Messages.getString("EntryDetailsView.MENU_ITEM_CUSTOM")); //$NON-NLS-1$

        none.setOnAction(evt -> updateRecurrenceRule(entry, null));
        everyDay.setOnAction(evt -> updateRecurrenceRule(entry, "RRULE:FREQ=DAILY")); //$NON-NLS-1$
        everyWeek.setOnAction(evt -> updateRecurrenceRule(entry, "RRULE:FREQ=WEEKLY")); //$NON-NLS-1$
        everyMonth.setOnAction(evt -> updateRecurrenceRule(entry, "RRULE:FREQ=MONTHLY")); //$NON-NLS-1$
        everyYear.setOnAction(evt -> updateRecurrenceRule(entry, "RRULE:FREQ=YEARLY")); //$NON-NLS-1$
        custom.setOnAction(evt -> showRecurrenceEditor(entry));

        recurrenceButton.getItems().setAll(none, everyDay, everyWeek, everyMonth, everyYear, new SeparatorMenuItem(), custom);
        recurrenceButton.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        // SI planner specific

        Label projectLabel = new Label("Project");
        Label projectTaskLabel = new Label("Project task");
        ComboBox<ProjectTask> projectTaskBox = new ComboBox<>();
        ComboBox<Project> projectBox = new ComboBox<>(projects);
        projectBox.setCellFactory(param -> {
            return new ListCell<>() {
                @Override protected void updateItem(Project item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(item.getName());
                        setGraphic(new Rectangle(10,10,
                                ProjectColor.fromId(item.getColor()).getColor()));
                    }
                }

            };
        });
        projectBox.setButtonCell(projectBox.getCellFactory().call(null));
        // project task cell factory (Text only for now)
        projectTaskBox.setCellFactory(param -> {
            return new ListCell<>() {
                @Override protected void updateItem(ProjectTask item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(item.getName());
                        setGraphic(null);
                    }
                }
            };
        });
        projectTaskBox.setButtonCell(projectTaskBox.getCellFactory().call(null));

        if (entry.getUserObject() instanceof Planning)
            projectBox.getSelectionModel().select(((Planning) entry.getUserObject()).getProjectTask().getProject()); else
            projectBox.getSelectionModel().select(((Timeregistration) entry.getUserObject()).getProjectTask().getProject());

        // add project tasks.
        for(ProjectTask projectTask : projectBox.getSelectionModel().getSelectedItem().getProjectTasks())
        {
            projectTaskBox.getItems().add(projectTask);
        }
        // select project task
        if(entry.getUserObject() instanceof Planning)
            projectTaskBox.getSelectionModel().select(((Planning) entry.getUserObject()).getProjectTask()); else
            projectTaskBox.getSelectionModel().select(((Timeregistration) entry.getUserObject()).getProjectTask());

        // project change event
        projectBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            ProjectTask newTask = newValue.getProjectTasks().iterator().next();
            if(entry.getUserObject() instanceof Planning)
                ((Planning) entry.getUserObject()).setProjectTask(newTask); else
                ((Timeregistration) entry.getUserObject()).setProjectTask(newTask);
            // update tasks box
            projectTaskBox.getItems().clear();
            for(ProjectTask projectTask : newValue.getProjectTasks())
            {
                projectTaskBox.getItems().add(projectTask);
            }
            projectTaskBox.getSelectionModel().select(newTask);

            // switch calendar
            for(Calendar calendar : calendarView.getCalendars())
            {
                if(calendar.getShortName().equals(newValue.getShortName()) &&
                        calendar.getName().equals(newValue.getName()))
                {
                    entry.setCalendar(calendar);
                    //calendarView.refreshData();
                }

            }
        });
        // project task change event
        projectTaskBox.valueProperty().addListener((obs,oldValue,newValue) -> {
            if(newValue == null) return;
            // update task and entry title
            if (entry.getUserObject() instanceof Planning)
                ((Planning) entry.getUserObject()).setProjectTask(newValue); else
                ((Timeregistration) entry.getUserObject()).setProjectTask(newValue);
            entry.setTitle(String.format("%s - %s",newValue.getProject().getName(),newValue.getName()));
        });


        Label timeLabel = new Label("Time type");
        ToggleGroup timeGroup = new ToggleGroup();
        RadioButton planningBtn = new RadioButton("Planned");
        planningBtn.setToggleGroup(timeGroup);

        RadioButton spentBtn = new RadioButton("Spent");
        spentBtn.setToggleGroup(timeGroup);
        if(entry.getUserObject() instanceof Planning)
            planningBtn.setSelected(true); else
                spentBtn.setSelected(true);
        // switch time type listener
        timeGroup.selectedToggleProperty().addListener((obs,oldValue,newValue) -> {
            if(newValue == planningBtn) {
                // convert from Timeregistration to Planning
                Timeregistration t = (Timeregistration)entry.getUserObject();
                Planning n = new Planning(AppFacade.appFacade.getDataSource().planningDao(),
                        0,false,
                        t.getStart(),t.getEnd(),t.getProjectTask(),t.getEmployee());
                if(AppFacade.appFacade.getDataSource().timeregistrationDao().deleteTimeregistration(t) == DaoResult.OP_OK)
                {
                    AppFacade.appFacade.getDataSource().planningDao().insertPlanning(n);
                    entry.setUserObject(n);
                }

            } else {
                // convert from Planning to Timeregistration
                Planning n = (Planning) entry.getUserObject();
                Timeregistration t = new Timeregistration(AppFacade.appFacade.getDataSource().timeregistrationDao(),
                        0,false,
                        n.getStart(),n.getEnd(),n.getProjectTask(),n.getEmployee());

                if(AppFacade.appFacade.getDataSource().planningDao().deletePlanning(n) == DaoResult.OP_OK)
                {
                    AppFacade.appFacade.getDataSource().timeregistrationDao().insertTimeregistration(t);
                    entry.setUserObject(t);
                }


            }
        });

        GridPane box = new GridPane();
        box.getStyleClass().add("content"); //$NON-NLS-1$
        box.add(fullDayLabel, 0, 0);
        box.add(fullDay, 1, 0);
        box.add(startDateLabel, 0, 1);
        box.add(startDateBox, 1, 1);
        box.add(endDateLabel, 0, 2);
        box.add(endDateBox, 1, 2);
        box.add(projectLabel,0,3);
        box.add(projectBox,1,3);
        box.add(projectTaskLabel,0,4);
        box.add(projectTaskBox,1,4);
  //      box.add(zoneLabel, 0, 3);
  //      box.add(zoneBox, 1, 3);
 //       box.add(recurrentLabel, 0, 4);
  //      box.add(recurrenceButton, 1, 4);
        box.add(timeLabel,0,5,1,2);
        box.add(planningBtn,1,5);
        box.add(spentBtn,1,6);
        box.add(summaryLabel, 1, 7);

        GridPane.setFillWidth(zoneBox, true);
        GridPane.setHgrow(zoneBox, Priority.ALWAYS);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setHalignment(HPos.RIGHT);
        col2.setHalignment(HPos.LEFT);

        box.getColumnConstraints().addAll(col1, col2);

        getChildren().add(box);

        startTimeField.visibleProperty().bind(Bindings.not(entry.fullDayProperty()));
        endTimeField.visibleProperty().bind(Bindings.not(entry.fullDayProperty()));

        // start date and time
        startDatePicker.valueProperty().addListener(evt -> entry.changeStartDate(startDatePicker.getValue(), true));
        startTimeField.valueProperty().addListener(evt -> entry.changeStartTime(startTimeField.getValue(), true));

        // end date and time
        endDatePicker.valueProperty().addListener(evt -> entry.changeEndDate(endDatePicker.getValue(), false));
        endTimeField.valueProperty().addListener(evt -> entry.changeEndTime(endTimeField.getValue(), false));

        // full day
        fullDay.setOnAction(evt -> entry.setFullDay(fullDay.isSelected()));

        // zone Id
        zoneBox.setOnAction(evt -> entry.setZoneId(zoneBox.getValue()));

        entry.recurrenceRuleProperty().addListener(it -> updateRecurrenceRuleButton(entry));

        updateRecurrenceRuleButton(entry);

        entry.recurrenceRuleProperty().addListener(it -> updateSummaryLabel(entry));
    }

    private void updateSummaryLabel(Entry<?> entry) {
        String rule = entry.getRecurrenceRule();
        String text = Util.convertRFC2445ToText(rule,
                entry.getStartDate());
        summaryLabel.setText(text);
    }

    private void showRecurrenceEditor(Entry<?> entry) {
        RecurrencePopup popup = new RecurrencePopup();
        RecurrenceView recurrenceView = popup.getRecurrenceView();
        String recurrenceRule = entry.getRecurrenceRule();
        if (recurrenceRule == null || recurrenceRule.trim().equals("")) { //$NON-NLS-1$
            recurrenceRule = "RRULE:FREQ=DAILY;"; //$NON-NLS-1$
        }
        recurrenceView.setRecurrenceRule(recurrenceRule);
        popup.setOnOkPressed(evt -> {
            String rrule = recurrenceView.getRecurrenceRule();
            entry.setRecurrenceRule(rrule);
        });

        Point2D anchor = recurrenceButton.localToScreen(0,
                recurrenceButton.getHeight());
        popup.show(recurrenceButton, anchor.getX(), anchor.getY());
    }

    private void updateRecurrenceRule(Entry<?> entry, String rule) {
        entry.setRecurrenceRule(rule);
    }

    private void updateRecurrenceRuleButton(Entry<?> entry) {
        String rule = entry.getRecurrenceRule();
        if (rule == null) {
            recurrenceButton.setText(Messages.getString("EntryDetailsView.NONE")); //$NON-NLS-1$
        } else {
            switch (rule.trim().toUpperCase()) {
                case "RRULE:FREQ=DAILY": //$NON-NLS-1$
                    recurrenceButton.setText(Messages.getString("EntryDetailsView.DAILY")); //$NON-NLS-1$
                    break;
                case "RRULE:FREQ=WEEKLY": //$NON-NLS-1$
                    recurrenceButton.setText(Messages.getString("EntryDetailsView.WEEKLY")); //$NON-NLS-1$
                    break;
                case "RRULE:FREQ=MONTHLY": //$NON-NLS-1$
                    recurrenceButton.setText(Messages.getString("EntryDetailsView.MONTHLY")); //$NON-NLS-1$
                    break;
                case "RRULE:FREQ=YEARLY": //$NON-NLS-1$
                    recurrenceButton.setText(Messages.getString("EntryDetailsView.YEARLY")); //$NON-NLS-1$
                    break;
                default:
                    recurrenceButton.setText(Messages.getString("EntryDetailsView.CUSTOM")); //$NON-NLS-1$
                    break;
            }
        }
    }
}
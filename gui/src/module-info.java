module gui {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.calendarfx.view;
    requires logic;
    requires di;
    exports Main to javafx.graphics;
    opens Login to javafx.fxml;
    opens Windows to javafx.fxml;
}

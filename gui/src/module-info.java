module gui {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.calendarfx.view;
    requires com.dlsc.preferencesfx;
    requires de.jensd.fx.fontawesomefx.fontawesome;
    requires de.jensd.fx.fontawesomefx.commons;
    //requires de.jensd.fx.glyphs.fontawesome;

    requires logic;
    requires di;
    exports Main to javafx.graphics;
    opens Login to javafx.fxml;
    opens Windows to javafx.fxml, javafx.base;
}

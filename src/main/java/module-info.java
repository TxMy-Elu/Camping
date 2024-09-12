module com.example.camping {
    requires javafx.fxml;
    requires java.sql;
    requires com.calendarfx.view;

    opens com.example.camping to javafx.fxml;
    exports com.example.camping;
}
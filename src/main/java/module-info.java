module com.example.camping {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.camping to javafx.fxml;
    exports com.example.camping;
}
module com.example.camping {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;

    opens com.example.camping to javafx.fxml;
    exports com.example.camping;

}
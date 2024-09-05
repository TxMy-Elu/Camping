module com.example.camping {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.internal.vm.compiler;

    opens com.example.camping to javafx.fxml;
    exports com.example.camping;
}
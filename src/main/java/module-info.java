module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.jmc to javafx.graphics, javafx.fxml;
    exports com.jmc;
    exports com.jmc.controller;
    opens com.jmc.controller to javafx.fxml, javafx.graphics;
}
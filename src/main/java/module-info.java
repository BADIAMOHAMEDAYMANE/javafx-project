module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.jmc to javafx.graphics, javafx.fxml;
    exports com.jmc;
}
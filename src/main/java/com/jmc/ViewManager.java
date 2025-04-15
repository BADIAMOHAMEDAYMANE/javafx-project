package com.jmc;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static Stage stage;

    public static void initialize(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("My JavaFX App");
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
    }

    public static void switchToView(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(ViewManager.class.getResource(fxmlPath));
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getWidth() {
        return WIDTH;
    }

    public static double getHeight() {
        return HEIGHT;
    }
}
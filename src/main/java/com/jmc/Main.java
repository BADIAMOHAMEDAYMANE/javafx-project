package com.jmc;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewManager.initialize(primaryStage);
        ViewManager.switchToView("/main-view.fxml");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
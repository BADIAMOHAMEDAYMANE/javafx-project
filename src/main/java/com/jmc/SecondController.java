package com.jmc;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SecondController {

    @FXML
    private void handleGoBack(ActionEvent event) {
        ViewManager.switchToView("/main-view.fxml");
    }
}
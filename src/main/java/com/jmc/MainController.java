package com.jmc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void handleButtonClick(ActionEvent event) {
        ViewManager.switchToView("/second-view.fxml");
    }

}

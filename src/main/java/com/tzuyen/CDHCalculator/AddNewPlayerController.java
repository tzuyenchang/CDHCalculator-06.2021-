package com.tzuyen.CDHCalculator;

import com.tzuyen.CDHCalculator.playerDataModel.CDHPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddNewPlayerController {

    @FXML
    private TextField AddNewPlayerNameField;


    public CDHPlayer getNewPlayer(){
        String playerName = AddNewPlayerNameField.getText();
        CDHPlayer newPlayer = new CDHPlayer(playerName, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return newPlayer;
    }
}


package com.tzuyen.CDHCalculator;

import com.tzuyen.CDHCalculator.playerDataModel.CDHPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditDialogController {

    @FXML
    private TextField EditPlayerNameField;
    @FXML
    private TextField EditScore8Field;
    @FXML
    private TextField EditScore9Field;
    @FXML
    private TextField EditScore10Field;
    @FXML
    private TextField EditScore11Field;
    @FXML
    private TextField EditScore12Field;
    @FXML
    private TextField EditScore13Field;
    @FXML
    private TextField EditScore14Field;
    @FXML
    private TextField EditScore15Field;
    @FXML
    private TextField EditScore16Field;

    private double calculateTotalScore(){
        double score8 = Double.parseDouble(EditScore8Field.getText());
        double score9 = Double.parseDouble(EditScore9Field.getText());
        double score10 = Double.parseDouble(EditScore10Field.getText());
        double score11 = Double.parseDouble(EditScore11Field.getText());
        double score12 = Double.parseDouble(EditScore12Field.getText());
        double score13 = Double.parseDouble(EditScore13Field.getText());
        double score14 = Double.parseDouble(EditScore14Field.getText());
        double score15 = Double.parseDouble(EditScore15Field.getText());
        double score16 = Double.parseDouble(EditScore16Field.getText());
        double total = score8 + score9 + score10 + score11 + score12 + score13 + score14 + score15 + score16;
        return  total;
    }


    public void editPlayerScore(CDHPlayer player){

        EditPlayerNameField.setText(player.getPlayerName());
        EditScore8Field. setText(player.getScore_8() + "");
        EditScore9Field.setText(player.getScore_9() + "");
        EditScore10Field.setText(player.getScore_10() + "");
        EditScore11Field.setText(player.getScore_11() + "");
        EditScore12Field.setText(player.getScore_12() + "");
        EditScore13Field.setText(player.getScore_13() + "");
        EditScore14Field.setText(player.getScore_14() + "");
        EditScore15Field.setText(player.getScore_15() + "");
        EditScore16Field.setText(player.getScore_16() + "");

    }

    public void updatePlayerScore(CDHPlayer player){
        player.setPlayerName(EditPlayerNameField.getText());
        player.setScore_8(Double.parseDouble(EditScore8Field.getText()));
        player.setScore_9(Double.parseDouble(EditScore9Field.getText()));
        player.setScore_10(Double.parseDouble(EditScore10Field.getText()));
        player.setScore_11(Double.parseDouble(EditScore11Field.getText()));
        player.setScore_12(Double.parseDouble(EditScore12Field.getText()));
        player.setScore_13(Double.parseDouble(EditScore13Field.getText()));
        player.setScore_14(Double.parseDouble(EditScore14Field.getText()));
        player.setScore_15(Double.parseDouble(EditScore15Field.getText()));
        player.setScore_16(Double.parseDouble(EditScore16Field.getText()));
        player.setScore_Total(calculateTotalScore());
    }
}

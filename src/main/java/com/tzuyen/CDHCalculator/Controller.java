package com.tzuyen.CDHCalculator;

import com.tzuyen.CDHCalculator.playerDataModel.CDHPlayer;
import com.tzuyen.CDHCalculator.playerDataModel.CDHPlayerData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    //all Fields from Buttons
    @FXML
    private Button Mission_1_1_Button;
    @FXML
    private Button Mission_1_2_Button;
    @FXML
    private Button Mission_1_3_Button;
    @FXML
    private Button Mission_1_4_Button;
    @FXML
    private Button Mission_2_1_Button;
    @FXML
    private Button Mission_2_2_Button;
    @FXML
    private Button Mission_3_1_Button;
    @FXML
    private Button Mission_3_2_Button;
    @FXML
    private Button Mission_3_3_Button;
    @FXML
    private CheckBox Checkbox_magic;
    @FXML
    private CheckBox Checkbox_tower;
    @FXML
    private Button Edit_Score_Button;
    @FXML
    private Button SelectedClear_Button;
    @FXML
    private Button AddNewPlayer_Button;
    @FXML
    private Button DeletePlayer_Button;

    @FXML
    private HBox ScoreOperator;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableView<CDHPlayer> CDHPlayersTable;

    private CDHPlayerData data;

    private CDHPlayer selectedPlayer;

    @FXML
    private Label processingLabel;

    @FXML
    private ComboBox LeagueComboBox;

    @FXML
    private ListView RankList;
    private ObservableList<String> RankDataList = FXCollections.observableArrayList();

    public void initialize() {
        RankListInit();
        data = new CDHPlayerData();
        data.loadCDHPlayer();
        CDHPlayersTable.setItems(data.getCDHPlayers());

        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if (selectedPlayer == null && CDHPlayersTable.getItems().size() == 0){
            setCanNotEdit();
        } else {
            setCanEdit();
        }
        Info("歡迎使用Crazy Defense聯盟計分器!");
    }

    private void RankListInit(){
        RankList.setItems(RankDataList);
        for (int i=1; i<30; i++){
            RankDataList.add(i+"");
        }
    }

    private void setCanEdit(){
        ScoreOperator.setDisable(false);
        DeletePlayer_Button.setDisable(false);
    }
    private void setCanNotEdit(){
        ScoreOperator.setDisable(true);
        DeletePlayer_Button.setDisable(true);
    }

    @FXML
    public void showAddPlayerDialog(){
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("新增成員");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddNewPlayerDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            AddNewPlayerController addNewPlayerController = fxmlLoader.getController();
            CDHPlayer newPlayer = addNewPlayerController.getNewPlayer();

            if (newPlayer.getPlayerName() == ""){
                System.out.println("is null");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("請輸入成員名稱");
                alert.showAndWait();
                return;
            }
            Info("成員 " + newPlayer.getPlayerName() + " 已新增!");
            data.addCDHPlayers(newPlayer);
            data.saveCDHPlayers();
            setCanEdit();
        }
    }

    @FXML
    public void showEditDialog(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("請選擇一位成員");
            alert.showAndWait();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("編輯分數");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EditDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        EditDialogController editPlayerScoreController = fxmlLoader.getController();
        editPlayerScoreController.editPlayerScore(selectedPlayer);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            editPlayerScoreController.updatePlayerScore(selectedPlayer);
            data.saveCDHPlayers();
            Info("已編輯 " + selectedPlayer.getPlayerName());
        }
        CDHPlayersTable.refresh();
    }

    @FXML
    public void DeletePlayer(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null){
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else {
            Alert alert_comfirn = new Alert(Alert.AlertType.CONFIRMATION);
            alert_comfirn.setHeaderText(null);
            alert_comfirn.setContentText("確定要刪除 " + selectedPlayer.getPlayerName() + "?");
            Optional<ButtonType> result = alert_comfirn.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                Info("成員 " + selectedPlayer.getPlayerName() + " 已刪除!");
                data.deleteCDHPlayers(selectedPlayer);
                data.saveCDHPlayers();
            }
        }
    }

    @FXML
    public void action_Mission_1_1(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();

        if(Checkbox_magic.isSelected()==true && Checkbox_tower.isSelected()==false){
            ScoreOperator(selectedPlayer, selectedLeague, 4.5);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因指定魔法卡(指定星)打指定怪加了4.5分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else if (Checkbox_magic.isSelected()==false && Checkbox_tower.isSelected()==true){
            ScoreOperator(selectedPlayer, selectedLeague, 4);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因指定塔(指定星)打指定怪加了4分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else {
            Alert alert_3 = new Alert(Alert.AlertType.INFORMATION);
            alert_3.setHeaderText(null);
            alert_3.setContentText("請選擇指定魔法卡或指定塔");
            alert_3.showAndWait();
            return;
        }
    }

    @FXML
    public void action_Mission_1_2(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();

        if(Checkbox_magic.isSelected()==true && Checkbox_tower.isSelected()==false){
            ScoreOperator(selectedPlayer, selectedLeague, 3.5);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因指定魔法卡(不限星)打指定怪加了3.5分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else if (Checkbox_magic.isSelected()==false && Checkbox_tower.isSelected()==true){
            ScoreOperator(selectedPlayer, selectedLeague, 3);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因指定塔(不限星)打指定怪加了3分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else {
            Alert alert_3 = new Alert(Alert.AlertType.INFORMATION);
            alert_3.setHeaderText(null);
            alert_3.setContentText("請選擇指定魔法卡或指定塔");
            alert_3.showAndWait();
            return;
        }
    }

    @FXML
    public void action_Mission_1_3(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();

        if(Checkbox_magic.isSelected()==true && Checkbox_tower.isSelected()==false){
            ScoreOperator(selectedPlayer, selectedLeague, 2.5);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因純指定魔法卡2.5分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else if (Checkbox_magic.isSelected()==false && Checkbox_tower.isSelected()==true){
            ScoreOperator(selectedPlayer, selectedLeague, 2);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因純指定塔加了2分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else {
            Alert alert_3 = new Alert(Alert.AlertType.INFORMATION);
            alert_3.setHeaderText(null);
            alert_3.setContentText("請選擇指定魔法卡或指定塔");
            alert_3.showAndWait();
            return;
        }
    }

    @FXML
    public void action_Mission_1_4(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();

        if(Checkbox_magic.isSelected()==true && Checkbox_tower.isSelected()==true){
            ScoreOperator(selectedPlayer, selectedLeague, 2);
            Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因純指定怪加了2分！");
            data.saveCDHPlayers();
            CDHPlayersTable.refresh();
        } else {
            Alert alert_3 = new Alert(Alert.AlertType.INFORMATION);
            alert_3.setHeaderText(null);
            alert_3.setContentText("純指定怪請把魔法卡與塔都勾選");
            alert_3.showAndWait();
            return;
        }
    }

    @FXML
    public void action_Mission_2_1(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();
        ScoreOperator(selectedPlayer, selectedLeague, 1);
        Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因純數量/純屬性/純星等加了1分！");
        data.saveCDHPlayers();
        CDHPlayersTable.refresh();
    }

    @FXML
    public void action_Mission_2_2(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();
        ScoreOperator(selectedPlayer, selectedLeague, 1);
        Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因英雄擊殺/英雄使用次數加了1分！");
        data.saveCDHPlayers();
        CDHPlayersTable.refresh();
    }


    @FXML
    public void action_Mission_3_1(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();
        ScoreOperator(selectedPlayer, selectedLeague, 1);
        Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因任務榜一加了1分！");
        data.saveCDHPlayers();
        CDHPlayersTable.refresh();
    }

    @FXML
    public void action_Mission_3_2(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();
        ScoreOperator(selectedPlayer, selectedLeague, 0.5);
        Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因任務榜二加了0.5分！");
        data.saveCDHPlayers();
        CDHPlayersTable.refresh();
    }

    @FXML
    public void action_Mission_3_3(){
        selectedPlayer = CDHPlayersTable.getSelectionModel().getSelectedItem();
        if(selectedPlayer == null) {
            Alert alert_1 = new Alert(Alert.AlertType.INFORMATION);
            alert_1.setHeaderText(null);
            alert_1.setContentText("請選擇一位成員");
            alert_1.showAndWait();
            return;
        } else if (LeagueComboBox.getSelectionModel().getSelectedItem() == null){
            Alert alert_2 = new Alert(Alert.AlertType.INFORMATION);
            alert_2.setHeaderText(null);
            alert_2.setContentText("請選擇目前聯盟");
            alert_2.showAndWait();
            return;
        }
        String selectedLeague = LeagueComboBox.getSelectionModel().getSelectedItem().toString();
        ScoreOperator(selectedPlayer, selectedLeague, 0.25);
        Info(selectedPlayer.getPlayerName() + " 在「" + selectedLeague + "」因任務榜三加了0.25分！");
        data.saveCDHPlayers();
        CDHPlayersTable.refresh();
    }

    private void ScoreOperator(CDHPlayer player, String LeagueNumber, double addScore){
        switch (LeagueNumber) {
            case "聯盟8":
                player.setScore_8(player.getScore_8() + addScore);
                player.setScore_Total( player.getScore_Total() + addScore);
                break;
            case "聯盟9":
                player.setScore_9(player.getScore_9() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟10":
                player.setScore_10(player.getScore_10() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟11":
                player.setScore_11(player.getScore_11() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟12":
                player.setScore_12(player.getScore_12() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟13":
                player.setScore_13(player.getScore_13() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟14":
                player.setScore_14(player.getScore_14() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟15":
                player.setScore_15(player.getScore_15() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
            case "聯盟16":
                player.setScore_16(player.getScore_16() + addScore);
                player.setScore_Total(player.getScore_Total() + addScore);
                break;
        }
    }

    private void Info(String information){
        processingLabel.setText(information);
    }


    public void MouseEnter_Mission(Button button){
        button.setScaleX(1.6);
        button.setScaleY(1.6);
    }
    public void MouseExit_Mission(Button button) {
        button.setScaleX(1.5);
        button.setScaleY(1.5);
    }

    @FXML
    public void MEnter_Mission_1_1(){ MouseEnter_Mission(Mission_1_1_Button); }
    @FXML
    public void MExit_Mission_1_1(){
        MouseExit_Mission(Mission_1_1_Button);
    }
    @FXML
    public void MEnter_Mission_1_2(){ MouseEnter_Mission(Mission_1_2_Button); }
    @FXML
    public void MExit_Mission_1_2(){
        MouseExit_Mission(Mission_1_2_Button);
    }
    @FXML
    public void MEnter_Mission_1_3(){ MouseEnter_Mission(Mission_1_3_Button); }
    @FXML
    public void MExit_Mission_1_3(){
        MouseExit_Mission(Mission_1_3_Button);
    }
    @FXML
    public void MEnter_Mission_1_4(){ MouseEnter_Mission(Mission_1_4_Button); }
    @FXML
    public void MExit_Mission_1_4(){
        MouseExit_Mission(Mission_1_4_Button);
    }
    @FXML
    public void MEnter_Mission_2_1(){ MouseEnter_Mission(Mission_2_1_Button); }
    @FXML
    public void MExit_Mission_2_1(){
        MouseExit_Mission(Mission_2_1_Button);
    }
    @FXML
    public void MEnter_Mission_2_2(){ MouseEnter_Mission(Mission_2_2_Button); }
    @FXML
    public void MExit_Mission_2_2(){
        MouseExit_Mission(Mission_2_2_Button);
    }
    @FXML
    public void MEnter_Mission_3_1(){ MouseEnter_Mission(Mission_3_1_Button); }
    @FXML
    public void MExit_Mission_3_1(){ MouseExit_Mission(Mission_3_1_Button); }
    @FXML
    public void MEnter_Mission_3_2(){ MouseEnter_Mission(Mission_3_2_Button); }
    @FXML
    public void MExit_Mission_3_2(){
        MouseExit_Mission(Mission_3_2_Button);
    }
    @FXML
    public void MEnter_Mission_3_3(){ MouseEnter_Mission(Mission_3_3_Button); }
    @FXML
    public void MExit_Mission_3_3(){
        MouseExit_Mission(Mission_3_3_Button);
    }


    @FXML
    public void MEnter_Edit_Score(){
        MouseEnter_Mission(Edit_Score_Button);
    }
    @FXML
    public void MExit_Edit_Score(){
        MouseExit_Mission(Edit_Score_Button);
    }
    @FXML
    public void MEnter_SelectedClear(){
        MouseEnter_Mission(SelectedClear_Button);
    }
    @FXML
    public void MExit_SelectedClear(){
        MouseExit_Mission(SelectedClear_Button);
    }
    @FXML
    public void MEnter_AddNewPlayer(){
        AddNewPlayer_Button.setScaleX(2.2);
        AddNewPlayer_Button.setScaleY(2.2);
    }
    @FXML
    public void MExit_AddNewPlayer(){
        AddNewPlayer_Button.setScaleX(2);
        AddNewPlayer_Button.setScaleY(2);
    }
    @FXML
    public void MEnter_DeletePlayer(){
        DeletePlayer_Button.setScaleX(2.2);
        DeletePlayer_Button.setScaleY(2.2);
    }
    @FXML
    public void MExit_DeletePlayer(){
        DeletePlayer_Button.setScaleX(2);
        DeletePlayer_Button.setScaleY(2);
    }

    @FXML
    public void TableRowSelectClear(){
        CDHPlayersTable.getSelectionModel().clearSelection();
        Info("歡迎使用Crazy Defense聯盟計分器!");
    }
}


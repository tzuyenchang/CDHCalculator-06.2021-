package com.tzuyen.CDHCalculator.playerDataModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CDHPlayer {
    private SimpleStringProperty playerName = new SimpleStringProperty("");
    private SimpleDoubleProperty Score_8 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_9 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_10 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_11 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_12 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_13 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_14 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_15 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_16 = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty Score_Total = new SimpleDoubleProperty(0);

    public CDHPlayer(){
    }

    public CDHPlayer(String playerName, double Score_8, double Score_9, double Score_10, double Score_11,
                     double Score_12, double Score_13, double Score_14, double Score_15, double Score_16, double Score_Total){
        this.playerName.set(playerName);
        this.Score_8.set(Score_8);
        this.Score_9.set(Score_9);
        this.Score_10.set(Score_10);
        this.Score_11.set(Score_11);
        this.Score_12.set(Score_12);
        this.Score_13.set(Score_13);
        this.Score_14.set(Score_14);
        this.Score_15.set(Score_15);
        this.Score_16.set(Score_16);
        this.Score_Total.set(Score_Total);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public SimpleStringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public double getScore_8() {
        return Score_8.get();
    }

    public SimpleDoubleProperty score_8Property() {
        return Score_8;
    }

    public void setScore_8(double score_8) {
        this.Score_8.set(score_8);
    }

    public double getScore_9() {
        return Score_9.get();
    }

    public SimpleDoubleProperty score_9Property() {
        return Score_9;
    }

    public void setScore_9(double score_9) {
        this.Score_9.set(score_9);
    }

    public double getScore_10() {
        return Score_10.get();
    }

    public SimpleDoubleProperty score_10Property() {
        return Score_10;
    }

    public void setScore_10(double score_10) {
        this.Score_10.set(score_10);
    }

    public double getScore_11() {
        return Score_11.get();
    }

    public SimpleDoubleProperty score_11Property() {
        return Score_11;
    }

    public void setScore_11(double score_11) {
        this.Score_11.set(score_11);
    }

    public double getScore_12() {
        return Score_12.get();
    }

    public SimpleDoubleProperty score_12Property() {
        return Score_12;
    }

    public void setScore_12(double score_12) {
        this.Score_12.set(score_12);
    }

    public double getScore_13() {
        return Score_13.get();
    }

    public SimpleDoubleProperty score_13Property() {
        return Score_13;
    }

    public void setScore_13(double score_13) {
        this.Score_13.set(score_13);
    }

    public double getScore_14() {
        return Score_14.get();
    }

    public SimpleDoubleProperty score_14Property() {
        return Score_14;
    }

    public void setScore_14(double score_14) {
        this.Score_14.set(score_14);
    }

    public double getScore_15() {
        return Score_15.get();
    }

    public SimpleDoubleProperty score_15Property() {
        return Score_15;
    }

    public void setScore_15(double score_15) {
        this.Score_15.set(score_15);
    }

    public double getScore_16() {
        return Score_16.get();
    }

    public SimpleDoubleProperty score_16Property() {
        return Score_16;
    }

    public void setScore_16(double score_16) {
        this.Score_16.set(score_16);
    }

    public double getScore_Total() {
        return Score_Total.get();
    }

    public SimpleDoubleProperty score_TotalProperty() {
        return Score_Total;
    }

    public void setScore_Total(double score_Total) {
        this.Score_Total.set(score_Total);
    }

    @Override
    public String toString() {
        return "CDHPlayer{" +
                "playerName=" + playerName +
                ", Score_8=" + Score_8 +
                ", Score_9=" + Score_9 +
                ", Score_10=" + Score_10 +
                ", Score_11=" + Score_11 +
                ", Score_12=" + Score_12 +
                ", Score_13=" + Score_13 +
                ", Score_14=" + Score_14 +
                ", Score_15=" + Score_15 +
                ", Score_16=" + Score_16 +
                ", Score_Total=" + Score_Total +
                '}';
    }
}

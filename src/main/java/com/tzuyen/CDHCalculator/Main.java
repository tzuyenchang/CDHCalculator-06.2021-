package com.tzuyen.CDHCalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("搞笑的部落-聯盟計分器");
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

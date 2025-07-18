package com.project.expenseTrackerUI;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainLayout mainLayout = new MainLayout();
        var scene = new Scene(mainLayout, 1280, 720);
        stage.setTitle("Expense Tracker App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
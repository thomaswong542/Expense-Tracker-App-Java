package com.project.expenseTrackerUI.components.componentBase;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CustomLabel extends Label {

    private final String name;
    private String textColor = "#FFFFFF";
    private double fontSize = 14.0d;

    public CustomLabel(String name){
        this.name = name;
        init();
    }

    private void init(){
        this.setText(this.name);
        this.setTextFill(Paint.valueOf(this.textColor));
        this.setFont(Font.font(this.fontSize));
    }

    public void setFontSize(double size){
        this.fontSize = size;
        this.setFont(Font.font(this.fontSize));
    }

    public void setTextColor(String textColor){
        this.textColor = textColor;
        this.setTextFill(Paint.valueOf(this.textColor));
    }

    public void setItalic(){
        this.setStyle("-fx-font-style: italic");
    }

}

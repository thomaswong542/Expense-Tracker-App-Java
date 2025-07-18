package com.project.expenseTrackerUI.components.componentBase;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CustomLabel extends Label {

    private String name;
    private String color = "#FFFFFF";
    private double fontSize = 14.0d;

    public CustomLabel(String name){
        this.name = name;
        init();
    }

    private void init(){
        this.setText(this.name);
        this.setTextFill(Paint.valueOf(this.color));
        this.setFont(Font.font(this.fontSize));
    }

    public void setName(String name){
        this.name = name;
        this.setText(this.name);
    }

    public void setColor(String color){
        this.color = color;
        this.setTextFill(Paint.valueOf(this.color));
    }

    public void setFontSize(double size){
        this.fontSize = size;
        this.setFont(Font.font(this.fontSize));
    }

}

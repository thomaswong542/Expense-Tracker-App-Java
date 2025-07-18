package com.project.expenseTrackerUI.components.componentBase;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

public class CustomButton extends Button {

    private String name;
    private String color = "#FFFFFF";
    private String bgColor = "#000000";
    private CornerRadii cornerRadius = new CornerRadii(5.0d);

    public CustomButton(String name){
        this.name = name;
        init();
    }

    private void init(){
        this.setText(this.name);
        this.setTextFill(Paint.valueOf(color));
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(this.bgColor), this.cornerRadius, Insets.EMPTY)));
    }

    public void setBgColor(String bgColor){
        this.bgColor = bgColor;
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(this.bgColor), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setColor(String color){
        this.color = color;
        this.setTextFill(Paint.valueOf(this.color));
    }

    public void setCornerRadius(double size){
        this.cornerRadius = new CornerRadii(size);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(this.bgColor), this.cornerRadius, Insets.EMPTY)));
    }

}

package com.project.expenseTrackerUI.components.componentBase;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

public class CustomMenuBar extends HBox {

    private Paint bgColor = Paint.valueOf("#000000");
    private Pos position = Pos.BASELINE_CENTER;

    public CustomMenuBar(){
        init();
    }

    private void init(){
        this.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(position);
    }

    public void setBgColor(String color){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setLeftPosition(){
        this.setAlignment(Pos.BASELINE_LEFT);
    }

    public void setRightPosition(){
        this.setAlignment(Pos.BASELINE_RIGHT);
    }

}

package com.project.expenseTrackerUI.components.componentBase;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class CustomGrid extends GridPane {

    private String color = "#484848";
    private double cornerRadii = 5.0d;
    private double vGap = 4.0d;
    private double hGap = 2.0d;

    public CustomGrid(){
        init();
    }

    private void init(){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(this.color), new CornerRadii(this.cornerRadii), Insets.EMPTY)));
        this.setVgap(this.vGap);
        this.setHgap(this.hGap);
        this.setPadding(new Insets(4.0d));
    }

    public void changeBgColor(String color) {
        this.color = color;
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(this.color), new CornerRadii(this.cornerRadii), Insets.EMPTY)));
    }

    public void changeVgap(double vGap){
        this.vGap = vGap;
        this.setVgap(vGap);
    }

    public void changeHgap(double hGap){
        this.hGap = hGap;
        this.setHgap(hGap);
    }

    public HBox setFormInput(Node node, String name){
        HBox hbox = new HBox();
        CustomLabel label = new CustomLabel(name);
        label.setFontSize(12);
        hbox.getChildren().addAll(label, node);
        return hbox;
    }

}

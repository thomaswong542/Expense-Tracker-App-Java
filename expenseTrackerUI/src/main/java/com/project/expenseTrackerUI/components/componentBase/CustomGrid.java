package com.project.expenseTrackerUI.components.componentBase;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class CustomGrid extends GridPane {

    private String bgColor = "#808080";
    private double cornerRadii = 5.0d;
    private double vGap = 4.0d;
    private double hGap = 2.0d;

    public CustomGrid(){
        init();
    }

    private void init(){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(this.bgColor), new CornerRadii(this.cornerRadii), Insets.EMPTY)));
        this.setVgap(this.vGap);
        this.setHgap(this.hGap);
        this.setPadding(new Insets(4.0d));
    }

    public HBox setFormInput(Node node, String name){
        HBox hbox = new HBox();
        CustomLabel label = new CustomLabel(name);
        label.setFontSize(12);
        label.setTextColor("#000000");
        hbox.getChildren().addAll(label, node);
        return hbox;
    }

}

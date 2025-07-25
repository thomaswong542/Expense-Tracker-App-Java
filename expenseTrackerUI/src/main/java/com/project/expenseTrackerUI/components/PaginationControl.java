package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.components.componentBase.CustomLabel;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;


public class PaginationControl extends HBox {

    private CustomButton leftBtn;
    private CustomButton rightBtn;
    private CustomLabel page;

    public PaginationControl(){
        init();
    }

    private void init(){
        leftBtn = new CustomButton("<");
        page = new CustomLabel("page ?");
        page.setTextColor("#000000");
        rightBtn = new CustomButton(">");
        this.getChildren().addAll(leftBtn, page, rightBtn);
        HBox.setMargin(leftBtn, new Insets(0, 1d, 0, 0));
        HBox.setMargin(page, new Insets(0, 1d, 0, 1d));
        HBox.setMargin(rightBtn, new Insets(0, 0, 0, 1d));
    }

}

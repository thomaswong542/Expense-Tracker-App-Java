package com.project.expenseTrackerUI.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class PaginationContainer extends HBox {

    PaginationControl paginationControl;

    public PaginationContainer(){
        init();
    }

    private void init(){
        paginationControl = new PaginationControl();
        this.getChildren().add(paginationControl);
        this.setAlignment(Pos.CENTER);
    }
}

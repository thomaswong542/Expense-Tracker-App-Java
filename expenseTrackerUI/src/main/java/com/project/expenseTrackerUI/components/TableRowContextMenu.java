package com.project.expenseTrackerUI.components;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TableRowContextMenu extends ContextMenu {

    private MenuItem updateMenuItem;

    public TableRowContextMenu(){
        init();
    }

    private void init(){
        updateMenuItem = new MenuItem("Update");
        this.getItems().add(updateMenuItem);
    }

    public MenuItem getUpdateMenuItem(){
        return this.updateMenuItem;
    }

}

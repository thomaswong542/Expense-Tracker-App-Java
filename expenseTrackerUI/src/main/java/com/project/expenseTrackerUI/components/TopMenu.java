package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.components.componentBase.CustomLabel;
import com.project.expenseTrackerUI.components.componentBase.CustomMenuBar;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class TopMenu extends CustomMenuBar {

    private CustomLabel homeTab;
    private CustomLabel expenseTab;

    public TopMenu(){
        init();
    }

    private void init(){
        this.homeTab = new CustomLabel("HOME");
        this.homeTab.setDisable(true);
        this.expenseTab = new CustomLabel("EXPENSE");

        this.getChildren().addAll(homeTab ,expenseTab);

        HBox.setMargin(homeTab, new Insets(2.0d, 20.0d, 2.0d, 20.0d));
        HBox.setMargin(expenseTab, new Insets(2.0d, 20.0d, 2.0d, 20.0d));

    }

}

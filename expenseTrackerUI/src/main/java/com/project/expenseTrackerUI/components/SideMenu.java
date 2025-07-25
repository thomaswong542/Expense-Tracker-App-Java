package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.components.componentBase.CustomLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class SideMenu extends VBox {

    private String bgColor = "#000000";
    private SideMenuItem dashboard;
    private SideMenuItem expenses;

    public SideMenu(){
        init();
    }

    private void init(){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(bgColor), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMinWidth(150.0d);

        VBox iconContainer = createIcon();

        dashboard = new SideMenuItem("Dashboard", "/img/dashboard_icon.png");
        dashboard.setDisable(true);
        expenses = new SideMenuItem("Expenses", "/img/expenses_icon.png");

        this.getChildren().addAll(iconContainer, dashboard, expenses);
        this.setSpacing(10d);
    }

    public void setBgColor(String bgColor){
        this.bgColor = bgColor;
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(bgColor), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public VBox createIcon(){
        VBox vbox = new VBox();
        CustomLabel appIcon = new CustomLabel("Expense");
        CustomLabel appIcon2 = new CustomLabel("Tracker");
        appIcon.setFontSize(20);
        appIcon.setItalic();
        appIcon2.setFontSize(20);
        appIcon2.setItalic();

        Separator sep = new Separator();

        vbox.getChildren().addAll(appIcon,appIcon2,sep);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinHeight(40d);
        return vbox;
    }

}

package com.project.expenseTrackerUI;

import com.project.expenseTrackerUI.components.*;
import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.event.EventHandler;
import com.project.expenseTrackerUI.model.Expense;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.net.http.HttpClient;

public class MainLayout extends VBox {

    private TopMenu topMenu;
    private FilterForm filterForm;
    private ExpenseTable expenseTable;
    private ExpenseForm expenseForm;
    private HttpClient client;

    public MainLayout() {
        init();
    }

    private void init() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));
        topMenu = new TopMenu();
        filterForm = new FilterForm();
        expenseTable = new ExpenseTable();
        expenseForm = new ExpenseForm();
        TableRowContextMenu contextMenu = new TableRowContextMenu();
        this.getChildren().addAll(topMenu, filterForm, expenseTable, expenseForm);

        VBox.setMargin(topMenu, new Insets(0, 0, 5.0d, 0));
        VBox.setMargin(filterForm, new Insets(10.0d, 20.0d, 10.0d, 20.0d));
        VBox.setMargin(expenseTable, new Insets(0, 20.0d, 0, 20.0d));
        VBox.setMargin(expenseForm, new Insets(10.0d, 20.0d, 10.0d, 20.0d));

        VBox.setVgrow(expenseTable, Priority.ALWAYS);

        // event handling
        client = HttpClient.newBuilder().build();
        ObservableList<Expense> expenseList = expenseTable.getList();
        CustomButton confirmBtn = filterForm.getConfirmBtn();
        CustomButton addBtn = expenseForm.getAddBtn();
        CustomButton updateBtn = expenseForm.getUpdateButton();
        CustomButton deleteBtn = expenseForm.getDeleteBtn();
        CustomButton cancelBtn = expenseForm.getCancelButton();

        EventHandler.getDbData(client, expenseList);
        EventHandler.filterConfirm(client, confirmBtn, filterForm, expenseList);
        EventHandler.addExpense(client, addBtn, expenseForm, expenseList);
        EventHandler.getRowAction(expenseTable, expenseForm, contextMenu);
        EventHandler.setContextMenuUpdateAction(contextMenu.getUpdateMenuItem(), expenseForm, expenseTable);
        EventHandler.updateExpense(client, updateBtn, expenseForm, expenseList);
        EventHandler.deleteExpense(client, deleteBtn, expenseTable, expenseList);
        EventHandler.cancelUpdate(cancelBtn, expenseForm);
    }

}

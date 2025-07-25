package com.project.expenseTrackerUI;

import com.project.expenseTrackerUI.components.*;
import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.event.EventHandler;
import com.project.expenseTrackerUI.model.Expense;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.net.http.HttpClient;

public class MainLayout extends HBox {

    private ExpenseTable expenseTable;
    private SideMenu sideMenu;
    private FormStack formStack;
    private PaginationContainer paginationContainer;
    private HttpClient client;

    public MainLayout() {
        init();
    }

    private void init() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));

        sideMenu = new SideMenu();
        paginationContainer = new PaginationContainer();
        paginationContainer.setDisable(true);
        expenseTable = new ExpenseTable();
        formStack = new FormStack();

        VBox rightPane = new VBox();
        rightPane.getChildren().addAll(paginationContainer, expenseTable, formStack);
        VBox.setVgrow(expenseTable, Priority.ALWAYS);
        VBox.setMargin(formStack, new Insets(5.0d, 10.0d, 5.0d, 10.0d));
        VBox.setMargin(expenseTable, new Insets(5.0d, 10.0d, 5.0d, 10.0d));
        VBox.setMargin(paginationContainer, new Insets(5.0d, 10.0d, 5.0d, 10.0d));

        this.getChildren().addAll(sideMenu, rightPane);
        HBox.setHgrow(rightPane, Priority.ALWAYS);

        // event handling
        client = HttpClient.newBuilder().build();
        ObservableList<Expense> expenseList = expenseTable.getList();
        TableRowContextMenu contextMenu = new TableRowContextMenu();
        MenuItem contextMenuUpdateBtn = contextMenu.getUpdateMenuItem();
        ExpenseForm expenseForm = formStack.getExpenseForm();
        FilterForm filterForm = formStack.getFilterForm();

        CustomButton confirmBtn = filterForm.getConfirmBtn();
        CustomButton addBtn = expenseForm.getAddBtn();
        CustomButton updateBtn = expenseForm.getUpdateButton();
        CustomButton deleteBtn = expenseForm.getDeleteBtn();
        CustomButton cancelBtn = expenseForm.getCancelButton();

        EventHandler.getDbData(client, expenseList);
        EventHandler.filterConfirm(client, confirmBtn, filterForm, expenseList);
        EventHandler.addExpense(client, addBtn, expenseForm, expenseList);
        EventHandler.getRowAction(expenseTable, expenseForm, contextMenu);
        EventHandler.setContextMenuUpdateAction(contextMenuUpdateBtn, expenseForm, expenseTable);
        EventHandler.updateExpense(client, updateBtn, expenseForm, expenseList);
        EventHandler.deleteExpense(client, deleteBtn, expenseTable, expenseList);
        EventHandler.cancelUpdate(cancelBtn, expenseForm);
    }

}

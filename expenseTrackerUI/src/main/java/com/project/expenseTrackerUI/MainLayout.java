package com.project.expenseTrackerUI;

import com.project.expenseTrackerUI.components.*;
import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.event.EventHandler;
import com.project.expenseTrackerUI.model.Expense;
import javafx.geometry.Insets;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class MainLayout extends HBox {

    private ExpenseTable expenseTable = new ExpenseTable();
    private SideMenu sideMenu = new SideMenu();
    private FormStack formStack = new FormStack();
    private PaginationContainer paginationContainer = new PaginationContainer();

    private ExpenseForm expenseForm = formStack.getExpenseForm();
    private FilterForm filterForm = formStack.getFilterForm();
    private boolean isUpdateMode = false;

    private EventHandler handler = new EventHandler();

    public MainLayout() {
        createView();
        DataBinding();
        createEvent();
    }

    private void DataBinding(){
        expenseForm.getDateInput().valueProperty().bindBidirectional(handler.getDateInputProperty());
        expenseForm.getCategoryInput().textProperty().bindBidirectional(handler.getCategoryInputProperty());
        expenseForm.getLocationInput().textProperty().bindBidirectional(handler.getLocationInputProperty());
        expenseForm.getShopInput().textProperty().bindBidirectional(handler.getShopInputProperty());
        expenseForm.getItemInput().textProperty().bindBidirectional(handler.getItemInputProperty());
        expenseForm.getCardInput().textProperty().bindBidirectional(handler.getCardInputProperty());
        expenseForm.getQuantityInput().getValueFactory().valueProperty().bindBidirectional(handler.getQuantityInputProperty());
        expenseForm.getPriceInput().getValueFactory().valueProperty().bindBidirectional(handler.getPriceInputProperty());

        filterForm.getStartDateInput().valueProperty().bindBidirectional(handler.getStartDateFilterProperty());
        filterForm.getEndDateInput().valueProperty().bindBidirectional(handler.getEndDateFilterProperty());
        filterForm.getCategoryInput().textProperty().bindBidirectional(handler.getCategoryFilterProperty());
        filterForm.getLocationInput().textProperty().bindBidirectional(handler.getLocationFilterProperty());
        filterForm.getShopInput().textProperty().bindBidirectional(handler.getShopFilterProperty());
        filterForm.getItemInput().textProperty().bindBidirectional(handler.getItemFilterProperty());
    }

    private void createView(){
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        paginationContainer.setDisable(true);

        VBox rightPane = new VBox();
        rightPane.getChildren().addAll(paginationContainer, expenseTable, formStack);
        VBox.setVgrow(expenseTable, Priority.ALWAYS);
        VBox.setMargin(formStack, new Insets(5.0d, 10.0d, 5.0d, 10.0d));
        VBox.setMargin(expenseTable, new Insets(5.0d, 10.0d, 5.0d, 10.0d));
        VBox.setMargin(paginationContainer, new Insets(5.0d, 10.0d, 5.0d, 10.0d));

        this.getChildren().addAll(sideMenu, rightPane);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
    }

    private void createEvent(){
        TableRowContextMenu contextMenu = new TableRowContextMenu();
        CustomButton addBtn = formStack.getExpenseForm().getAddBtn();
        CustomButton updateBtn = formStack.getExpenseForm().getUpdateButton();
        CustomButton deleteBtn = formStack.getExpenseForm().getDeleteBtn();
        CustomButton cancelBtn = formStack.getExpenseForm().getCancelButton();
        CustomButton confirmBtn = formStack.getFilterForm().getConfirmBtn();

        expenseTable.setItems(handler.getExpenseList());

        handler.getDbData();

        expenseTable.setRowFactory(event -> {
            TableRow<Expense> row = new TableRow<>();

            row.setOnMouseClicked(mouseEvent -> {
                Expense rowData = row.getItem();

                if (mouseEvent.getClickCount() == 2 && !row.isEmpty() && mouseEvent.getButton() == MouseButton.PRIMARY){
                    handler.fillFormData(rowData);
                }

                if (mouseEvent.getButton() == MouseButton.SECONDARY && !row.isEmpty()){
                    row.setContextMenu(contextMenu);
                }
            });

            return row;
        });

        contextMenu.getUpdateMenuItem().setOnAction(event -> {
            int selectedRow_idx = expenseTable.getSelectionModel().getSelectedIndex();
            handler.fillUpdateFormData(expenseTable.getSelectionModel().getSelectedItem(), selectedRow_idx);
            if (!isUpdateMode){
                switchToUpdateMode(addBtn, deleteBtn, updateBtn, cancelBtn);
            }
            isUpdateMode = true;
        });

        cancelBtn.setOnAction(event -> {
            switchToAddMode(addBtn, deleteBtn, updateBtn, cancelBtn);
            isUpdateMode = false;
        });

        addBtn.setOnAction(event -> handler.addExpense());

        updateBtn.setOnAction(event -> {
            handler.updateExpense();
            switchToAddMode(addBtn, deleteBtn, updateBtn, cancelBtn);
            isUpdateMode = false;
        });

        deleteBtn.setOnAction(event -> handler.deleteExpense(expenseTable.getSelectionModel().getSelectedItem(),
                expenseTable.getSelectionModel().getSelectedIndex()));

        confirmBtn.setOnAction(event -> handler.filterConfirm());
    }

    private void switchToUpdateMode(CustomButton addBtn, CustomButton deleteBtn, CustomButton updateBtn, CustomButton cancelBtn) {
        int colA = GridPane.getColumnIndex(addBtn);
        int rowA = GridPane.getRowIndex(addBtn);

        int colD = GridPane.getColumnIndex(deleteBtn);
        int rowD = GridPane.getRowIndex(deleteBtn);

        expenseForm.getChildren().remove(addBtn);
        expenseForm.getChildren().remove(deleteBtn);

        expenseForm.add(updateBtn, colA, rowA);
        expenseForm.add(cancelBtn, colD, rowD);
    }

    private void switchToAddMode(CustomButton addBtn, CustomButton deleteBtn, CustomButton updateBtn, CustomButton cancelBtn) {
        int colU = GridPane.getColumnIndex(updateBtn);
        int rowU = GridPane.getRowIndex(updateBtn);

        int colC = GridPane.getColumnIndex(cancelBtn);
        int rowC = GridPane.getRowIndex(cancelBtn);

        expenseForm.getChildren().remove(updateBtn);
        expenseForm.getChildren().remove(cancelBtn);

        expenseForm.add(addBtn, colU, rowU);
        expenseForm.add(deleteBtn, colC, rowC);
    }

}

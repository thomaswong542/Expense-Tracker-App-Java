package com.project.expenseTrackerUI.event;

import com.project.expenseTrackerUI.components.ExpenseForm;
import com.project.expenseTrackerUI.components.FilterForm;
import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.model.Expense;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class EventHandler {

    private final static String uriBase = "http://localhost:8080/v1/expense";
    private static int update_id;
    private static int updateRow_Index;

    public static void getRowAction(TableView<Expense> tableView, ExpenseForm form, ContextMenu menu){
        tableView.setRowFactory(event -> {
            TableRow<Expense> row = new TableRow<>();

            row.setOnMouseClicked(mouseEvent -> {
                Expense rowData = row.getItem();

                if(mouseEvent.getClickCount() == 2 && !row.isEmpty() && mouseEvent.getButton() == MouseButton.PRIMARY){
                    FormHandler.fillFormData(rowData, form);
                }

                if (mouseEvent.getButton() == MouseButton.SECONDARY && !row.isEmpty()){
                    row.setContextMenu(menu);
                }
            });
            return row;
        });
    }

    public static void setContextMenuUpdateAction(MenuItem menuItem, ExpenseForm expenseForm, TableView<Expense> tableView){
        menuItem.setOnAction(event -> {
            Expense rowData = tableView.getSelectionModel().getSelectedItem();
            FormHandler.fillFormData(rowData, expenseForm);

            update_id = rowData.getId();
            updateRow_Index = tableView.getSelectionModel().getSelectedIndex();

            CustomButton addBtn = expenseForm.getAddBtn();
            CustomButton deleteBtn = expenseForm.getDeleteBtn();
            CustomButton updateBtn = expenseForm.getUpdateButton();
            CustomButton cancelButton = expenseForm.getCancelButton();
            if (expenseForm.getChildren().contains(addBtn) && expenseForm.getChildren().contains(deleteBtn)) {
                EventHandlerTools.switchButtons(expenseForm, addBtn, deleteBtn, updateBtn, cancelButton);
            }
        });
    }

    public static void filterConfirm(HttpClient client, Button btn, FilterForm filterForm, ObservableList<Expense> expenseList){
        btn.setOnAction(event -> {
            HashMap<String, String> filterFormDataMap = FormHandler.getFilterFormData(filterForm);

            StringBuilder str = EventHandlerTools.getUrlParam(filterFormDataMap);
            String uri = uriBase + str;

            HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "GET", uri, "");

            if (response != null){
                Expense[] expenses = DataHandler.jsonToExpense(response);

                List<Expense> ls = Arrays.stream(expenses).filter(expense -> !expense.getDeleted()).toList();
                expenseList.clear();
                expenseList.addAll(ls);
                FormHandler.clearFilterForm(filterForm);
            }
        });
    }

    public static void addExpense(HttpClient client, Button btn, ExpenseForm expenseForm, ObservableList<Expense> expensesList){
        btn.setOnAction(event -> {
            if (!FormHandler.expenseFormValidate(expenseForm)){
                EventHandlerTools.showAlert("Date cannot be null. Price and Quantity must be number and cannot be null");
                return;
            }

            Expense expense = FormHandler.getExpenseFormData(expenseForm);
            String jsonString = DataHandler.expenseToString(expense);
            HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "POST", uriBase, jsonString);

            if (response != null && response.statusCode() == 201){
                Expense[] expenses = DataHandler.jsonToExpense(response);
                int id = expenses[0].getId();
                expense.setId(id);
                expense.setDeleted(false);
                expensesList.addFirst(expense);
            }
            else{
                EventHandlerTools.showAlert("Error in sending data to db");
            }
        });
    }

    public static void updateExpense(HttpClient client, Button btn, ExpenseForm expenseForm, ObservableList<Expense> expenseList){
        btn.setOnAction(event -> {
            if (!FormHandler.expenseFormValidate(expenseForm)){
                EventHandlerTools.showAlert("Date cannot be null. Price and Quantity must be number and cannot be null");
                return;
            }

            Expense expense = FormHandler.getExpenseFormData(expenseForm);
            String jsonString = DataHandler.expenseToString(expense);

            String uri = uriBase + "/" + update_id;
            HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "PUT", uri, jsonString);

            if (response != null && response.statusCode() == 200){
                expenseList.set(updateRow_Index, expense);
                expenseList.get(updateRow_Index).setId(update_id);
                expenseList.get(updateRow_Index).setDeleted(false);

                CustomButton addBtn = expenseForm.getAddBtn();
                CustomButton deleteBtn = expenseForm.getDeleteBtn();
                CustomButton updateBtn = expenseForm.getUpdateButton();
                CustomButton cancelButton = expenseForm.getCancelButton();
                EventHandlerTools.switchButtons(expenseForm, updateBtn, cancelButton, addBtn, deleteBtn);
            }
            else{
                EventHandlerTools.showAlert("Error in updating data in db");
            }
        });
    }

    public static void deleteExpense(HttpClient client, Button btn, TableView<Expense> tableView, ObservableList<Expense> expenseList){
        btn.setOnAction(event -> {
            Expense selectedExpense = tableView.getSelectionModel().getSelectedItem();
            if (selectedExpense == null){
                EventHandlerTools.showAlert("You must select a row");
                return;
            }

            int delete_id = selectedExpense.getId();
            int deleteRow_index = tableView.getSelectionModel().getSelectedIndex();

            String uri = uriBase + '/' + delete_id;

            HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "DELETE", uri, "");

            if (response != null && response.statusCode() == 200){
                expenseList.remove(deleteRow_index);
            }
            else{
                EventHandlerTools.showAlert("Error on deleting");
            }
        });
    }

    public static void cancelUpdate(Button btn, ExpenseForm expenseForm){
        btn.setOnAction(event -> {
            CustomButton addBtn = expenseForm.getAddBtn();
            CustomButton deleteBtn = expenseForm.getDeleteBtn();
            CustomButton updateBtn = expenseForm.getUpdateButton();
            CustomButton cancelButton = expenseForm.getCancelButton();

            EventHandlerTools.switchButtons(expenseForm, updateBtn, cancelButton, addBtn, deleteBtn);
        });
    }

    public static void getDbData(HttpClient client, ObservableList<Expense> expensesList){
        HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "GET", uriBase, "");

        if (response != null) {
            Expense[] expenses = DataHandler.jsonToExpense(response);

            List<Expense> ls = Arrays.stream(expenses).filter(expense -> !expense.getDeleted()).toList();
            expensesList.addAll(ls);
        }
    }

}

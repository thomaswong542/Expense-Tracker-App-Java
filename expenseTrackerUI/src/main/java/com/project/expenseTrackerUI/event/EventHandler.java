package com.project.expenseTrackerUI.event;

import com.project.expenseTrackerUI.model.Expense;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EventHandler {

    private ObjectProperty<LocalDate> dateInputProperty;
    private StringProperty categoryInputProperty;
    private StringProperty locationInputProperty;
    private StringProperty shopInputProperty;
    private StringProperty itemInputProperty;
    private StringProperty cardInputProperty;
    private ObjectProperty<Integer> quantityInputProperty;
    private ObjectProperty<Double> priceInputProperty;

    private ObjectProperty<LocalDate> startDateFilterProperty;
    private ObjectProperty<LocalDate> endDateFilterProperty;
    private StringProperty categoryFilterProperty;
    private StringProperty locationFilterProperty;
    private StringProperty shopFilterProperty;
    private StringProperty itemFilterProperty;

    private final String uriBase = "http://localhost:8080/v1/expense";
    private int update_id;
    private int currentUpdateRow_idx;
    private ObservableList<Expense> expenseList = FXCollections.observableArrayList();

    private HttpClient client;

    public EventHandler(){
        dateInputProperty = new SimpleObjectProperty<>();
        categoryInputProperty = new SimpleStringProperty();
        locationInputProperty = new SimpleStringProperty();
        shopInputProperty = new SimpleStringProperty();
        itemInputProperty = new SimpleStringProperty();
        cardInputProperty = new SimpleStringProperty();
        quantityInputProperty = new SimpleObjectProperty<>();
        priceInputProperty = new SimpleObjectProperty<>();

        startDateFilterProperty = new SimpleObjectProperty<>();
        endDateFilterProperty = new SimpleObjectProperty<>();
        categoryFilterProperty = new SimpleStringProperty();
        locationFilterProperty = new SimpleStringProperty();
        shopFilterProperty = new SimpleStringProperty();
        itemFilterProperty = new SimpleStringProperty();

        client = HttpClient.newBuilder().build();
    }

    public ObjectProperty<LocalDate> getDateInputProperty(){
        return this.dateInputProperty;
    }

    public StringProperty getCategoryInputProperty(){
        return this.categoryInputProperty;
    }

    public StringProperty getLocationInputProperty(){
        return this.locationInputProperty;
    }

    public StringProperty getShopInputProperty(){
        return this.shopInputProperty;
    }

    public StringProperty getItemInputProperty(){
        return this.itemInputProperty;
    }

    public StringProperty getCardInputProperty(){
        return this.cardInputProperty;
    }

    public ObjectProperty<Integer> getQuantityInputProperty(){
        return this.quantityInputProperty;
    }

    public ObjectProperty<Double> getPriceInputProperty(){
        return this.priceInputProperty;
    }

    public ObjectProperty<LocalDate> getStartDateFilterProperty() {
        return this.startDateFilterProperty;
    }

    public ObjectProperty<LocalDate> getEndDateFilterProperty(){
        return this.endDateFilterProperty;
    }

    public StringProperty getCategoryFilterProperty(){
        return this.categoryFilterProperty;
    }

    public StringProperty getLocationFilterProperty(){
        return this.locationFilterProperty;
    }

    public StringProperty getShopFilterProperty(){
        return this.shopFilterProperty;
    }

    public StringProperty getItemFilterProperty(){
        return this.itemFilterProperty;
    }

    public ObservableList<Expense> getExpenseList(){
        return this.expenseList;
    }

    private boolean expenseFormValidation(){
        return dateInputProperty.get() != null && quantityInputProperty.get() != null && priceInputProperty.get() != null;
    }

    private boolean filterFormValidation(String startDate, String endDate){
        if (startDate == null && endDate == null) {
            return true;
        }
        if ((startDate != null && endDate != null) && LocalDate.parse(endDate).isAfter(LocalDate.parse(startDate))){
            return true;
        }
        return false;
    }

    private HashMap<String, String> getFilterFormData(){
        HashMap<String, String> map = new HashMap<>();

        String startDate = startDateFilterProperty.get() != null ? startDateFilterProperty.get().toString() : null;
        String endDate = endDateFilterProperty.get() != null ? endDateFilterProperty.get().toString() : null;
        String category = categoryFilterProperty.get();
        String location = locationFilterProperty.get();
        String shop = shopFilterProperty.get();
        String item = itemFilterProperty.get();

        if (!filterFormValidation(startDate, endDate)){
            EventHandlerTools.showAlert("startDate and endDate must both exist or both non exist, startDate must be earlier than endDate");
        }
        else {
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("category", category);
            map.put("location", location);
            map.put("shop", shop);
            map.put("item", item);
        }

        return map;
    }

    private void clearFilterForm(){
        startDateFilterProperty.set(null);
        endDateFilterProperty.set(null);
        categoryFilterProperty.set(null);
        locationFilterProperty.set(null);
        shopFilterProperty.set(null);
        itemFilterProperty.set(null);
    }

    public void fillFormData(Expense expense){
        dateInputProperty.set(expense.getDate());
        categoryInputProperty.set(expense.getCategory());
        locationInputProperty.set(expense.getLocation());
        shopInputProperty.set(expense.getShop());
        itemInputProperty.set(expense.getItem());
        cardInputProperty.set(expense.getCard());
        quantityInputProperty.set(expense.getQuantity());
        priceInputProperty.set(expense.getPrice());
    }

    public void fillUpdateFormData(Expense expense, int selectedRow_id){
        fillFormData(expense);
        update_id = expense.getId();
        currentUpdateRow_idx = selectedRow_id;
    }

    public void addExpense() {
        if (!expenseFormValidation()){
            EventHandlerTools.showAlert("Date must be in date format, quantity and price must be number and cannot be empty");
            return;
        }
        Expense expense = new Expense(dateInputProperty.get(), categoryInputProperty.get(), locationInputProperty.get(), shopInputProperty.get(),
                itemInputProperty.get(), cardInputProperty.get(), quantityInputProperty.get(), priceInputProperty.get());
        String jsonData = DataHandler.expenseToJson(expense);

        HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "POST", uriBase, jsonData);

        if (response != null && response.statusCode() == 201){
            Expense[] expenses = DataHandler.jsonToExpense(response);
            int id = expenses[0].getId();
            expense.setId(id);
            expenseList.addFirst(expense);
        }
        else{
            EventHandlerTools.showAlert("Error in sending data to db");
        }
    }


    public void updateExpense(){
        if (!expenseFormValidation()){
            EventHandlerTools.showAlert("Date must be in date format, quantity and price must be number and cannot be empty");
            return;
        }
        Expense expense = new Expense(dateInputProperty.get(), categoryInputProperty.get(), locationInputProperty.get(), shopInputProperty.get(),
                itemInputProperty.get(), cardInputProperty.get(), quantityInputProperty.get(), priceInputProperty.get());
        String jsonData = DataHandler.expenseToJson(expense);

        String uri = uriBase + "/" + update_id;
        HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "PUT", uri, jsonData);

        if (response != null && response.statusCode() == 200){
            expenseList.set(currentUpdateRow_idx, expense);
            expenseList.get(currentUpdateRow_idx).setId(update_id);
        }
        else{
            EventHandlerTools.showAlert("Error in updating data in db");
        }
    }

    public void deleteExpense(Expense selectedExpense, int deleteRow_index){
        if (selectedExpense == null){
            EventHandlerTools.showAlert("You must select a row");
            return;
        }

        int delete_id = selectedExpense.getId();

        String uri = uriBase + '/' + delete_id;

        HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "DELETE", uri, "");

        if (response != null && response.statusCode() == 200){
            expenseList.remove(deleteRow_index);
        }
        else{
            EventHandlerTools.showAlert("Error on deleting");
        }
    }

    public void getDbData(){
        HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "GET", uriBase, "");

        if (response != null) {
            Expense[] expenses = DataHandler.jsonToExpense(response);

            List<Expense> ls = Arrays.stream(expenses).filter(expense -> !expense.getDeleted()).toList();
            expenseList.addAll(ls);
        }

    }

    public void filterConfirm(){
        HashMap<String, String> filterFormDataMap = getFilterFormData();
        StringBuilder str = EventHandlerTools.getUrlParam(filterFormDataMap);
        String uri = uriBase + str;

        HttpResponse<byte[]> response = RequestHandler.sendHttpRequest(client, "GET", uri, "");

        if (response != null){
            Expense[] expenses = DataHandler.jsonToExpense(response);

            List<Expense> ls = Arrays.stream(expenses).filter(expense -> !expense.getDeleted()).toList();
            expenseList.clear();
            expenseList.addAll(ls);
            clearFilterForm();
        }
    }

}

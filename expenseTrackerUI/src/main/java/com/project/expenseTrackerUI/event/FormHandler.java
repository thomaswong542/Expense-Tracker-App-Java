package com.project.expenseTrackerUI.event;

import com.project.expenseTrackerUI.components.ExpenseForm;
import com.project.expenseTrackerUI.components.FilterForm;
import com.project.expenseTrackerUI.model.Expense;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.HashMap;

public abstract class FormHandler {

    public static boolean expenseFormValidate(ExpenseForm form) {
        if (form.getDateValue() == null) {
            return false;
        }
        try {
            form.getQuantityValue();
            form.getPriceValue();
            return true;
        } catch (NullPointerException | NumberFormatException ex) {
            return false;
        }
    }

    public static void fillFormData(Expense expense, ExpenseForm form) {
        LocalDate date = expense.getDate();
        String category = expense.getCategory();
        String location = expense.getLocation();
        String shop = expense.getShop();
        String item = expense.getItem();
        String card = expense.getCard();
        int quantity = expense.getQuantity();
        double price = expense.getPrice();

        form.setDateInput(date);
        form.setCategoryInput(category);
        form.setLocationInput(location);
        form.setShopInput(shop);
        form.setItemInput(item);
        form.setCardInput(card);
        form.setQuantityInput(quantity);
        form.setPriceInput(price);
    }

    public static Expense getExpenseFormData(ExpenseForm expenseForm) {
        LocalDate date = expenseForm.getDateValue();
        String category = expenseForm.getCategoryValue();
        String location = expenseForm.getLocationValue();
        String shop = expenseForm.getShopValue();
        String item = expenseForm.getItemValue();
        String card = expenseForm.getCardValue();
        int quantity = expenseForm.getQuantityValue();
        double price = expenseForm.getPriceValue();

        return new Expense(date, category, location, shop, item, card, quantity, price);
    }

    public static HashMap<String, String> getFilterFormData(FilterForm filterForm) {
        HashMap<String, String> map = new HashMap<>();

        String startDate = filterForm.getStartDateValue() != null ? filterForm.getStartDateValue().toString() : "";
        String endDate = filterForm.getEndDateValue() != null ? filterForm.getEndDateValue().toString() : "";
        String category = filterForm.getCategoryValue();
        String location = filterForm.getLocationValue();
        String shop = filterForm.getShopValue();
        String item = filterForm.getItemValue();

        if ((startDate.isEmpty() && !endDate.isEmpty()) || (!startDate.isEmpty() && endDate.isEmpty())) {
            EventHandlerTools.showAlert("startDate and endDate must both exist or both non exist");
        } else if (!startDate.isEmpty() && LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
            EventHandlerTools.showAlert("startDate must be earlier than endDate");
        } else {
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("category", category);
            map.put("location", location);
            map.put("shop", shop);
            map.put("item", item);
        }

        return map;
    }

    public static void clearFilterForm(FilterForm filterForm){
        DatePicker startDatePicker = filterForm.getStartDateInput();
        DatePicker endDatePicker = filterForm.getEndDateInput();
        TextField categoryInput = filterForm.getCategoryInput();
        TextField locationInput = filterForm.getLocationInput();
        TextField shopInput = filterForm.getShopInput();
        TextField itemInput = filterForm.getItemInput();

        startDatePicker.getEditor().clear();
        startDatePicker.setValue(null);

        endDatePicker.getEditor().clear();
        endDatePicker.setValue(null);

        categoryInput.clear();
        categoryInput.setText(null);

        locationInput.clear();
        locationInput.setText(null);

        shopInput.clear();
        shopInput.setText(null);

        itemInput.clear();
        itemInput.setText(null);
    }

}

package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.components.componentBase.CustomGrid;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class ExpenseForm extends CustomGrid {

    private DatePicker dateInput;
    private TextField categoryInput;
    private TextField locationInput;
    private TextField shopInput;
    private TextField itemInput;
    private TextField cardInput;
    private Spinner<Integer> quantityInput;
    private Spinner<Double> priceInput;

    private CustomButton deleteBtn;
    private CustomButton addBtn;
    private CustomButton updateButton;
    private CustomButton cancelButton;

    public ExpenseForm(){
        init();
    }

    private void init(){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(23);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(23);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(23);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(23);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(8);

        this.getColumnConstraints().addAll(column1, column2, column3, column4, column5);

        dateInput = new DatePicker();
        dateInput.setEditable(false);
        HBox dateContainer = setFormInput(dateInput, "Date: ");

        categoryInput = new TextField();
        HBox categoryContainer = setFormInput(categoryInput, "Category: ");

        locationInput = new TextField();
        HBox locationContainer = setFormInput(locationInput, "Location: ");

        shopInput = new TextField();
        HBox shopContainer = setFormInput(shopInput, "Shop: ");

        itemInput = new TextField();
        HBox itemContainer = setFormInput(itemInput, "Item: ");

        cardInput = new TextField();
        HBox cardContainer = setFormInput(cardInput, "Card: ");

        quantityInput = new Spinner<>(0, 20, 0, 1);
        quantityInput.setEditable(true);
        HBox quantityContainer = setFormInput(quantityInput, "Quantity: ");

        priceInput = new Spinner<>(-2, 1000.0d, 0, 0.01);
        priceInput.setEditable(true);
        HBox priceContainer = setFormInput(priceInput, "Price: ");

        addBtn = new CustomButton("Add");
        deleteBtn = new CustomButton("Delete");
        updateButton = new CustomButton("Update");
        cancelButton = new CustomButton("Cancel");

        this.add(dateContainer, 0, 0);
        this.add(categoryContainer, 1, 0);
        this.add(locationContainer, 2, 0);
        this.add(shopContainer, 3, 0);
        this.add(deleteBtn, 4, 0);
        this.add(itemContainer, 0, 1);
        this.add(cardContainer, 1, 1);
        this.add(quantityContainer, 2, 1);
        this.add(priceContainer, 3, 1);
        this.add(addBtn, 4, 1);
    }

    public CustomButton getAddBtn(){
        return this.addBtn;
    }

    public CustomButton getUpdateButton(){
        return this.updateButton;
    }

    public CustomButton getCancelButton(){
        return this.cancelButton;
    }

    public CustomButton getDeleteBtn(){
        return this.deleteBtn;
    }

    public DatePicker getDateInput(){
        return this.dateInput;
    }

    public TextField getCategoryInput() {
        return this.categoryInput;
    }

    public TextField getLocationInput() {
        return this.locationInput;
    }

    public TextField getShopInput() {
        return this.shopInput;
    }

    public TextField getItemInput() {
        return this.itemInput;
    }

    public TextField getCardInput() {
        return this.cardInput;
    }

    public Spinner<Integer> getQuantityInput() {
        return this.quantityInput;
    }

    public Spinner<Double> getPriceInput() {
        return this.priceInput;
    }

}

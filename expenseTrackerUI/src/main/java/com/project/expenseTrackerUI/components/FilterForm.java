package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.components.componentBase.CustomGrid;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class FilterForm extends CustomGrid {

    private DatePicker startDateInput;
    private DatePicker endDateInput;
    private TextField categoryInput;
    private TextField locationInput;
    private TextField shopInput;
    private TextField itemInput;
    private CustomButton confirmBtn;

    public FilterForm(){
        init();
    }

    private void init(){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(10);

        this.getColumnConstraints().addAll(column1, column2, column3, column4);

        startDateInput = new DatePicker();
        startDateInput.setEditable(false);
        HBox startDateContainer = setFormInput(startDateInput, "StartDate: ");

        endDateInput = new DatePicker();
        endDateInput.setEditable(false);
        HBox endDateContainer = setFormInput(endDateInput, "EndDate: ");

        categoryInput = new TextField();
        HBox categoryContainer = setFormInput(categoryInput, "Category: ");

        locationInput = new TextField();
        HBox locationContainer = setFormInput(locationInput, "Location: ");

        shopInput = new TextField();
        HBox shopContainer = setFormInput(shopInput, "Shop: ");

        itemInput = new TextField();
        HBox itemContainer = setFormInput(itemInput, "Item: ");

        confirmBtn = new CustomButton("Confirm");

        this.add(startDateContainer, 0, 0);
        this.add(endDateContainer, 1, 0);
        this.add(categoryContainer, 2, 0);
        this.add(locationContainer, 0, 1);
        this.add(shopContainer, 1, 1);
        this.add(itemContainer, 2, 1);
        this.add(confirmBtn, 3, 1);
    }

    public CustomButton getConfirmBtn() {
        return this.confirmBtn;
    }

    public LocalDate getStartDateValue(){
        return this.startDateInput.getValue();
    }

    public LocalDate getEndDateValue(){
        return this.endDateInput.getValue();
    }

    public String getCategoryValue(){
        return this.categoryInput.getCharacters().toString();
    }

    public String getLocationValue(){
        return this.locationInput.getCharacters().toString();
    }

    public String getShopValue(){
        return this.shopInput.getCharacters().toString();
    }

    public String getItemValue(){
        return this.itemInput.getCharacters().toString();
    }

    public DatePicker getStartDateInput(){
        return this.startDateInput;
    }

    public DatePicker getEndDateInput(){
        return this.endDateInput;
    }

    public TextField getCategoryInput(){
        return this.categoryInput;
    }

    public TextField getLocationInput() {
        return this.locationInput;
    }

    public TextField getShopInput() {
        return this.shopInput;
    }

    public TextField getItemInput(){
        return this.itemInput;
    }

}

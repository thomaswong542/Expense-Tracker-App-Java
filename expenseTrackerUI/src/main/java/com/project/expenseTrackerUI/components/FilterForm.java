package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import com.project.expenseTrackerUI.components.componentBase.CustomGrid;
import com.project.expenseTrackerUI.components.componentBase.CustomLabel;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.Date;

public class FilterForm extends CustomGrid {

    private DatePicker startDateInput;
    private DatePicker endDateInput;
    private TextField categoryInput;
    private TextField locationInput;
    private TextField shopInput;
    private CustomButton confirmBtn;

    public FilterForm(){
        init();
    }

    private void init(){
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(34);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33);

        this.getColumnConstraints().addAll(column1, column2, column3);

        startDateInput = new DatePicker();
        startDateInput.setEditable(false);
        HBox startDateContainer = setFormInput(startDateInput, "StartDate: ");
        startDateContainer.setAlignment(Pos.BOTTOM_LEFT);

        endDateInput = new DatePicker();
        endDateInput.setEditable(false);
        HBox endDateContainer = setFormInput(endDateInput, "EndDate: ");
        endDateContainer.setAlignment(Pos.BOTTOM_CENTER);

        categoryInput = new TextField();
        HBox categoryContainer = setFormInput(categoryInput, "Category: ");
        categoryContainer.setAlignment(Pos.BOTTOM_RIGHT);

        locationInput = new TextField();
        HBox locationContainer = setFormInput(locationInput, "Location: ");
        locationContainer.setAlignment(Pos.BOTTOM_LEFT);

        shopInput = new TextField();
        HBox shopContainer = setFormInput(shopInput, "Shop: ");
        shopContainer.setAlignment(Pos.BOTTOM_CENTER);

        confirmBtn = new CustomButton("Confirm");
        HBox confirmContainer = setFormInput(confirmBtn, "");
        confirmContainer.setAlignment(Pos.BOTTOM_RIGHT);

        CustomLabel filterLabel = new CustomLabel("Filter Form");
        filterLabel.setFontSize(14);
        HBox filterContainer = setFormInput(filterLabel, "");
        filterContainer.setAlignment(Pos.BOTTOM_CENTER);

        this.add(filterContainer, 1, 0);
        this.add(startDateContainer, 0, 1);
        this.add(endDateContainer, 1, 1);
        this.add(categoryContainer, 2, 1);
        this.add(locationContainer, 0, 2);
        this.add(shopContainer, 1, 2);
        this.add(confirmContainer, 2, 2);
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
        return locationInput;
    }

    public TextField getShopInput() {
        return shopInput;
    }

}

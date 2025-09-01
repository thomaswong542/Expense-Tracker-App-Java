package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.model.Expense;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ExpenseTable extends TableView<Expense> {

    private TableColumn<Expense, LocalDate> dateCol;
    private TableColumn<Expense, String> categoryCol;
    private TableColumn<Expense, String> locationCol;
    private TableColumn<Expense, String> shopCol;
    private TableColumn<Expense, String> itemCol;
    private TableColumn<Expense, String> cardCol;
    private TableColumn<Expense, Integer> quantityCol;
    private TableColumn<Expense, Double> priceCol;

    public ExpenseTable(){
        init();
    }

    private void init(){
        dateCol = new TableColumn<>("Date");
        defineColumn(dateCol, "date", 0.1);

        categoryCol = new TableColumn<>("Category");
        defineColumn(categoryCol, "category", 0.1);

        locationCol = new TableColumn<>("Location");
        defineColumn(locationCol, "location", 0.1);

        shopCol = new TableColumn<>("Shop");
        defineColumn(shopCol, "shop", 0.2);

        itemCol = new TableColumn<>("Item");
        defineColumn(itemCol, "item", 0.2);

        cardCol = new TableColumn<>("Card");
        defineColumn(cardCol, "card", 0.1);

        quantityCol = new TableColumn<>("Quantity");
        defineColumn(quantityCol, "quantity", 0.1);

        priceCol = new TableColumn<>("Price");
        defineColumn(priceCol, "price", 0.1);

        this.getColumns().setAll(dateCol, categoryCol, locationCol, shopCol, itemCol, cardCol, quantityCol, priceCol);
    };

    private void defineColumn(TableColumn<Expense, ?> col, String fieldName, double colSize){
        col.setCellValueFactory(new PropertyValueFactory<>(fieldName));
        col.prefWidthProperty().bind(this.widthProperty().multiply(colSize));
        col.setResizable(false);
        col.setReorderable(false);
    }

}

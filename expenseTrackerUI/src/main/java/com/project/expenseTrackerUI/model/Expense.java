package com.project.expenseTrackerUI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Expense {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean deleted;
    private ObjectProperty<LocalDate> date;
    private StringProperty category;
    private StringProperty location;
    private StringProperty shop;
    private StringProperty item;
    private StringProperty card;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public Expense(@JsonProperty("date") LocalDate userDate, @JsonProperty("category") String userCategory,
                   @JsonProperty("location") String userLocation, @JsonProperty("shop") String userShop,
                   @JsonProperty("item") String userItem, @JsonProperty("card") String userCard,
                   @JsonProperty("quantity") int userQuantity, @JsonProperty("price") double userPrice){

        this.dateProperty().set(userDate);
        this.categoryProperty().set(userCategory);
        this.locationProperty().set(userLocation);
        this.shopProperty().set(userShop);
        this.itemProperty().set(userItem);
        this.cardProperty().set(userCard);
        this.quantityProperty().set(userQuantity);
        this.priceProperty().set(userPrice);
    }

    public ObjectProperty<LocalDate> dateProperty(){
        if (this.date == null){
            this.date = new SimpleObjectProperty<LocalDate>(this, "date");
        }
        return this.date;
    }

    public StringProperty categoryProperty(){
        if (this.category == null){
            this.category = new SimpleStringProperty(this, "category");
        }
        return this.category;
    }

    public StringProperty locationProperty(){
        if (this.location == null){
            this.location = new SimpleStringProperty(this, "location");
        }
        return this.location;
    }

    public StringProperty shopProperty(){
        if (this.shop == null){
            this.shop = new SimpleStringProperty(this, "shop");
        }
        return this.shop;
    }

    public StringProperty itemProperty(){
        if (this.item == null){
            this.item = new SimpleStringProperty(this, "item");
        }
        return this.item;
    }

    public StringProperty cardProperty(){
        if (this.card == null){
            this.card = new SimpleStringProperty(this, "card");
        }
        return this.card;
    }

    public IntegerProperty quantityProperty(){
        if (this.quantity == null){
            this.quantity = new SimpleIntegerProperty(this, "category");
        }
        return this.quantity;
    }

    public DoubleProperty priceProperty(){
        if (this.price == null){
            this.price = new SimpleDoubleProperty(this, "price");
        }
        return this.price;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getCategory(){
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getShop() {
        return shop.get();
    }

    public void setShop(String shop) {
        this.shop.set(shop);
    }

    public String getItem() {
        return item.get();
    }

    public void setItem(String item) {
        this.item.set(item);
    }

    public String getCard() {
        return card.get();
    }

    public void setCard(String card) {
        this.card.set(card);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public boolean getDeleted(){
        return this.deleted;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

}

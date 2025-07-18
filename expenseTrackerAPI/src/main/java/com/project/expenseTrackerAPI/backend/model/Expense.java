package com.project.expenseTrackerAPI.backend.model;

import java.time.LocalDate;

public class Expense {

    private int id;
    private LocalDate date;
    private String category;
    private String location;
    private String shop;
    private String item;
    private String card;
    private int quantity;
    private double price;
    private boolean deleted = false;

    public Expense(LocalDate userDate, String userCategory, String userLocation, String userShop, String userItem,
                   String userCard, int userQuantity, double userPrice){
        this.date = userDate;
        this.category = userCategory;
        this.location = userLocation;
        this.shop = userShop;
        this.item = userItem;
        this.card = userCard;
        this.quantity = userQuantity;
        this.price = userPrice;
    }

    public int getId(){
        return this.id;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getCategory(){
        return this.category;
    }

    public String getLocation(){
        return this.location;
    }

    public String getShop(){
        return this.shop;
    }

    public String getItem(){
        return this.item;
    }

    public String getCard(){
        return this.card;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public double getPrice(){
        return this.price;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setCategory(String cat){
        this.category = cat;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setShop(String shop){
        this.shop = shop;
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setCard(String card){
        this.card = card;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }







}

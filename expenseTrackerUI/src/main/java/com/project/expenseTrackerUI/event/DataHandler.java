package com.project.expenseTrackerUI.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.expenseTrackerUI.model.Expense;

import java.net.http.HttpResponse;

public abstract class DataHandler {

    public static Expense[] jsonToExpense(HttpResponse<String> response){
        ObjectMapper obj = new ObjectMapper();
        obj.findAndRegisterModules();

        String json = response.body();
        Expense[] expenses;
        try {
            expenses = obj.readValue(json, Expense[].class);
        } catch (JsonProcessingException e) {
            EventHandlerTools.showAlert("Unable to parse response");
            throw new RuntimeException(e);
        }
        return expenses;
    }

    public static String expenseToString(Expense expense){
        ObjectMapper obj = new ObjectMapper();
        obj.findAndRegisterModules();

        String jsonString;
        try {
            jsonString = obj.writerWithDefaultPrettyPrinter().writeValueAsString(expense);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

}

package com.project.expenseTrackerUI.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.expenseTrackerUI.model.Expense;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpResponse;
import java.util.zip.GZIPInputStream;

public abstract class DataHandler {

    public static Expense[] jsonToExpense(HttpResponse<byte[]> response){
        ObjectMapper obj = new ObjectMapper();
        obj.findAndRegisterModules();

        byte[] responseBytes = response.body();
        String json = readGzipData(responseBytes);

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
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    private static String readGzipData(byte[] bytes){
        GZIPInputStream decompressedIS;
        try {
            decompressedIS = new GZIPInputStream(new ByteArrayInputStream(bytes));
        } catch (IOException e){
            EventHandlerTools.showAlert("Unable to get gzip response");
            throw new RuntimeException(e);
        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(decompressedIS));
        StringBuilder result = new StringBuilder();
        String line;
        while (true) {
            try {
                line = bf.readLine();
                if (line == null){
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            result.append(line);
        }
        return result.toString();
    }

}

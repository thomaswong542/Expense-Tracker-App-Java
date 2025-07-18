package com.project.expenseTrackerAPI.backend.data;

import com.project.expenseTrackerAPI.backend.customException.BadRequestException;
import com.project.expenseTrackerAPI.backend.model.Expense;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class DataValidation {

    public static boolean expenseNullCheck(Expense expense){
        if (expense == null){
            return false;
        }

        ArrayList<Boolean> expenseFields = new ArrayList<>(List.of(
                expense.getDate() == null,
                expense.getCategory() == null,
                expense.getLocation() == null,
                expense.getShop() == null,
                expense.getItem() == null,
                expense.getCard() == null
        ));

        ArrayList<Boolean> trueCheck = new ArrayList<>(List.of(false));

        return trueCheck.containsAll(expenseFields);
    }

    public static LocalDate dateCheck(DateTimeFormatter formatter, String dateVal) throws BadRequestException {
        LocalDate startDate;

        try {
            startDate = LocalDate.parse(dateVal, formatter);
        } catch (DateTimeException ex){
            throw new BadRequestException("Wrong startDate and endDate format");
        }

        return startDate;
    }

}

package com.project.expenseTrackerAPI.backend.customException;

public class BadRequestException extends Exception{

    private String message;

    public BadRequestException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}

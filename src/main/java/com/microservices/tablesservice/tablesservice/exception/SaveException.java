package com.microservices.tablesservice.tablesservice.exception;

public class SaveException extends Exception {

    private String message;

    public SaveException(String message)  {
        this.message = "Could not create the Table -> " + message;
    }

}

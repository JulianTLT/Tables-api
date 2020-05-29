package com.microservices.tablesservice.tablesservice.exception;

import lombok.Data;

@Data
public class DeleteException extends Exception {

    private String message;

    public DeleteException(String message)  {
        this.message = "Could not delete the Table -> " + message;
    }
}

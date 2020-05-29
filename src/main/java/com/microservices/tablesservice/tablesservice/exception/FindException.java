package com.microservices.tablesservice.tablesservice.exception;

import lombok.Data;

@Data
public class FindException extends Exception {
    private String message;

    public FindException(String message)  {
        this.message = "Could not Find any Table with the criteria-> " + message;
    }

}

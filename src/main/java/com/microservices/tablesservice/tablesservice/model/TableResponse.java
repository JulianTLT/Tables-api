package com.microservices.tablesservice.tablesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class TableResponse {
    HttpStatus httpStatus;
    Table table;
    String error;

    public TableResponse(HttpStatus httpStatus, Table table) {
        this(httpStatus, table, null);
    }
}

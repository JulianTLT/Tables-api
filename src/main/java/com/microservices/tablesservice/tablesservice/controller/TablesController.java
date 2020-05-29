package com.microservices.tablesservice.tablesservice.controller;

import com.microservices.tablesservice.tablesservice.exception.FindException;
import com.microservices.tablesservice.tablesservice.exception.SaveException;
import com.microservices.tablesservice.tablesservice.model.Table;
import com.microservices.tablesservice.tablesservice.model.TableResponse;
import com.microservices.tablesservice.tablesservice.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/tables")
@Slf4j
public class TablesController {

    private TableService tableService;

    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<TableResponse> createTable(@Valid @RequestBody Table table) {
        TableResponse tableResponse;
        try {
            Table add = tableService.add(table);
            tableResponse = new TableResponse(HttpStatus.OK, add);
        } catch (SaveException e) {
            tableResponse = new TableResponse(HttpStatus.BAD_REQUEST, null, e.getMessage());
        }
        return new ResponseEntity<>(tableResponse, tableResponse.getHttpStatus());
    }

    @PutMapping
    public ResponseEntity<TableResponse> updateTable(@Valid @RequestBody Table table) {
        TableResponse tableResponse;
        try {
            Table update = tableService.update(table);
            tableResponse = new TableResponse(HttpStatus.OK, update);
        } catch (SaveException e) {
            tableResponse = new TableResponse(HttpStatus.BAD_REQUEST, null, e.getMessage());
        }
        return new ResponseEntity<>(tableResponse, tableResponse.getHttpStatus());
    }

    @DeleteMapping
    public ResponseEntity<TableResponse> deleteTable(@RequestParam("tableId") Long tableId) {
        TableResponse tableResponse;
        try {
            Table delete = tableService.delete(tableId);
            tableResponse = new TableResponse(HttpStatus.OK, delete);
        } catch (Exception e) {
            tableResponse = new TableResponse(HttpStatus.BAD_REQUEST, null, e.getMessage());
        }
        return new ResponseEntity<>(tableResponse, tableResponse.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableResponse> getTableById(@PathVariable("id") Long tableId) {
        TableResponse tableResponse;
        try {
            Table table = tableService.findById(tableId);
            tableResponse = new TableResponse(HttpStatus.OK, table);
        } catch (FindException e) {
            tableResponse = new TableResponse(HttpStatus.BAD_REQUEST, null, e.getMessage());
        }
        return new ResponseEntity<>(tableResponse, tableResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<List<Table>> getTableByRestaurantId(@RequestParam("restaurantId") Long restaurantId) {
        ResponseEntity<List<Table>> response;
        try {
            List<Table> list = tableService.findByRestaurantId(restaurantId);
            response = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}

package com.microservices.tablesservice.tablesservice.service;

import com.microservices.tablesservice.tablesservice.exception.DeleteException;
import com.microservices.tablesservice.tablesservice.exception.FindException;
import com.microservices.tablesservice.tablesservice.exception.SaveException;
import com.microservices.tablesservice.tablesservice.model.Table;

import java.util.List;

public interface TableService {
    Table add(Table table) throws SaveException;
    Table Update(Table table) throws SaveException;
    Table delete(Long tableId) throws FindException, DeleteException;
    Table findById(Long tableId) throws FindException;
    List<Table> findByRestaurantId(Long restaurantId);
}

package com.microservices.tablesservice.tablesservice.converter;

import com.microservices.tablesservice.tablesservice.entity.TableEntity;
import com.microservices.tablesservice.tablesservice.model.Table;

public class TableConverter implements Converter<TableEntity, Table> {

    @Override
    public Table entityToModel(TableEntity entity) {
        Table table = new Table();
        table.setQuantity(entity.getQuantity());
        table.setRestaurantId(entity.getRestaurantId());
        return table;
    }

    @Override
    public TableEntity modelToEntity(Table model) {
        TableEntity entity = new TableEntity();
        entity.setQuantity(model.getQuantity());
        entity.setRestaurantId(model.getRestaurantId());
        return entity;
    }
}

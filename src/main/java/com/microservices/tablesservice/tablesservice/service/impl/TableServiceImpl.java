package com.microservices.tablesservice.tablesservice.service.impl;

import com.microservices.tablesservice.tablesservice.converter.TableConverter;
import com.microservices.tablesservice.tablesservice.entity.TableEntity;
import com.microservices.tablesservice.tablesservice.exception.DeleteException;
import com.microservices.tablesservice.tablesservice.exception.FindException;
import com.microservices.tablesservice.tablesservice.exception.SaveException;
import com.microservices.tablesservice.tablesservice.model.Table;
import com.microservices.tablesservice.tablesservice.repository.TableDao;
import com.microservices.tablesservice.tablesservice.service.TableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class TableServiceImpl implements TableService {

    private TableDao tableDao;
    private TableConverter converter;

    public TableServiceImpl(TableDao tableDao, TableConverter converter) {
        this.tableDao = tableDao;
        this.converter = converter;
    }

    @Override
    public Table add(Table table) throws SaveException {
        TableEntity entity = converter.modelToEntity(table);
        TableEntity save;
        try {
            save = tableDao.save(entity);
        } catch (Exception e){
            throw new SaveException("Table object" + table);
        }

        return converter.entityToModel(save);
    }

    @Override
    public Table Update(Table table) throws SaveException {
        TableEntity entity = converter.modelToEntity(table);
        TableEntity save;
        try {
            save = tableDao.save(entity);
        } catch (Exception e){
            throw new SaveException("Table object" + table);
        }

        return converter.entityToModel(save);
    }

    @Override
    public Table delete(Long tableId) throws FindException, DeleteException {
        Optional<TableEntity> optEntity = tableDao.findById(tableId);
        if(optEntity.isPresent()) {
            try {
                tableDao.delete(optEntity.get());
            } catch (Exception e) {
                throw new DeleteException("TableId "+tableId);
            }
        } else {
            throw new FindException("TableId "+ tableId);
        }
        return converter.entityToModel(optEntity.get());
    }

    @Transactional(readOnly = true)
    @Override
    public Table findById(Long tableId) throws FindException {
        Optional<TableEntity> optEntity = tableDao.findById(tableId);
        Table table;
        if(optEntity.isPresent()) {
            table = converter.entityToModel(optEntity.get());
        }else {
            throw new FindException("TableId "+ tableId);
        }
        return table;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Table> findByRestaurantId(Long restaurantId) {
        List<TableEntity> allByRestaurantId = tableDao.findAllByRestaurantId(restaurantId);
        List<Table> tableList = allByRestaurantId.stream()
                .map(entity -> converter.entityToModel(entity))
                .collect(Collectors.toList());
        return tableList;
    }
}

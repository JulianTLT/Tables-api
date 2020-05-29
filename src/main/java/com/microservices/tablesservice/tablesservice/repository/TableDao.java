package com.microservices.tablesservice.tablesservice.repository;

import com.microservices.tablesservice.tablesservice.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableDao extends JpaRepository<TableEntity, Long> {
    List<TableEntity> findAllByRestaurantId(Long restaurantId);
}

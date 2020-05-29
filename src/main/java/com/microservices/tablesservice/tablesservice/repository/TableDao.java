package com.microservices.tablesservice.tablesservice.repository;

import com.microservices.tablesservice.tablesservice.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableDao extends JpaRepository<TableEntity, Long> {
}

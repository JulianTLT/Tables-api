package com.microservices.tablesservice.tablesservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Tables")
@Data
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "restaurantId")
    private long restaurantId;
}

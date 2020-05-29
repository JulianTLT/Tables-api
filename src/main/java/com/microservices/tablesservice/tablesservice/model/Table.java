package com.microservices.tablesservice.tablesservice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Table {
    Long id;
    int quantity;
    Long restaurantId;
}

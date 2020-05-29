package com.microservices.tablesservice.tablesservice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Table {
    int quantity;
    Long restaurantId;
}

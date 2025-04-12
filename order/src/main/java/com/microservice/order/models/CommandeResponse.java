package com.microservice.order.models;

import com.microservice.order.beans.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandeResponse {
    private Order order;
    private Client client;
    private Product product;
}


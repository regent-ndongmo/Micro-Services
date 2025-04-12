package com.microservice.order.models;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String nom;
    private String adresse;
}

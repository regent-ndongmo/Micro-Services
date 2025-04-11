package com.microservice.order.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class orderController {

    @GetMapping
    public ResponseEntity<String> getOrder() {
        return ResponseEntity.ok("Hello World");
    }
}

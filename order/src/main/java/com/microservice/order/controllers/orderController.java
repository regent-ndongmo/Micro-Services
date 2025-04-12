package com.microservice.order.controllers;

import com.microservice.order.models.CommandeResponse;
import com.microservice.order.services.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class orderController {

    private final CommandeService commandeService;

    public orderController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<String> getOrder() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public ResponseEntity<CommandeResponse> createCommade(@RequestParam Long clientId, @RequestParam Long productId) {
        CommandeResponse commandeResponse = commandeService.createCommande(clientId, productId);
        return ResponseEntity.ok(commandeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeResponse> getCommande(@PathVariable Long id) {
        CommandeResponse response = commandeService.getCommandeDetails(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<CommandeResponse>> getAllCommande() {
        List<CommandeResponse> commandeResponses = commandeService.getAllCommande();
        return ResponseEntity.ok(commandeResponses);
    }
}

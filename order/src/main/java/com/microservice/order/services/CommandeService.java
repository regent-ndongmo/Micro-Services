package com.microservice.order.services;

import com.microservice.order.models.CommandeResponse;

import java.util.List;

public interface CommandeService {
    public CommandeResponse createCommande(Long clientid, Long productId);
    public CommandeResponse getCommandeDetails(Long commandeId);
    public List<CommandeResponse> getAllCommande();
}

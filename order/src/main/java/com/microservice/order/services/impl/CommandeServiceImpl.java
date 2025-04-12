package com.microservice.order.services.impl;

import com.microservice.order.beans.Order;
import com.microservice.order.models.Client;
import com.microservice.order.models.CommandeResponse;
import com.microservice.order.models.Product;
import com.microservice.order.openFeignConfig.ClientRestClient;
import com.microservice.order.openFeignConfig.ProductFeignProduct;
import com.microservice.order.repository.OrderRepository;
import com.microservice.order.services.CommandeService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final ClientRestClient clientRestClient;
    private final ProductFeignProduct productFeignProduct;
    private final OrderRepository orderRepository;

    public CommandeServiceImpl(ClientRestClient clientRestClient, ProductFeignProduct productFeignProduct, OrderRepository orderRepository) {
        this.clientRestClient = clientRestClient;
        this.productFeignProduct = productFeignProduct;
        this.orderRepository = orderRepository;
    }

    @Override
    public CommandeResponse createCommande(Long clientId, Long produitId) {
        try {
            // Appel au microservice client
            Client client = clientRestClient.getClientById(clientId);
            if (client == null) {
                throw new RuntimeException("Client introuvable avec l'ID: " + clientId);
            }

            // Appel au microservice produit
            Product produit = productFeignProduct.getProduitById(produitId);
            if (produit == null) {
                throw new RuntimeException("Produit introuvable avec l'ID: " + produitId);
            }

            // Création de la commande
            Order order = new Order();
            order.setUser_id(clientId);
            order.setProduct_id(produitId);
            orderRepository.save(order);

            return new CommandeResponse(order, client, produit);

        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Ressource non trouvée : " + e.getMessage(), e);
        } catch (FeignException e) {
            throw new RuntimeException("Service indisponible. Veuillez réessayer plus tard : " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erreur inattendue lors de la création de la commande", e);
        }
    }

    @Override
    public CommandeResponse getCommandeDetails(Long commandeId) {
        try {
            Order order = orderRepository.findById(commandeId)
                    .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'ID: " + commandeId));

            Client client = clientRestClient.getClientById(order.getUser_id());
            if (client == null) {
                throw new RuntimeException("Client introuvable avec l'ID: " + order.getUser_id());
            }

            Product produit = productFeignProduct.getProduitById(order.getProduct_id());
            if (produit == null) {
                throw new RuntimeException("Produit introuvable avec l'ID: " + order.getProduct_id());
            }

            return new CommandeResponse(order, client, produit);

        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Ressource non trouvée : " + e.getMessage(), e);
        } catch (FeignException e) {
            throw new RuntimeException("Erreur lors de l'appel à un service externe : " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erreur inattendue lors de la récupération de la commande", e);
        }
    }

    @Override
    public List<CommandeResponse> getAllCommande() {
        try {
            List<Order> orders = orderRepository.findAll();

            List<CommandeResponse> responses = new ArrayList<>();

            for (Order order : orders) {
                try {
                    Client client = clientRestClient.getClientById(order.getUser_id());
                    Product produit = productFeignProduct.getProduitById(order.getProduct_id());

                    responses.add(new CommandeResponse(order, client, produit));

                } catch (FeignException.NotFound e) {
                    // On ignore ou on ajoute un placeholder si client ou produit manquant
                    System.err.println("Client ou produit introuvable pour la commande ID: " + order.getId());
                } catch (FeignException e) {
                    System.err.println("Erreur lors de l'appel d'un service externe pour la commande ID: " + order.getId());
                }
            }

            return responses;

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des commandes", e);
        }
    }

}

package com.microservice.order.openFeignConfig;

import com.microservice.order.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT")
public interface ClientRestClient {
    @GetMapping("client/api/clients/{id}")
    Client getClientById(@PathVariable("id") Long id);
}
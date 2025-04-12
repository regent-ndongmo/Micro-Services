package com.microservice.order.repository;

import com.microservice.order.beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

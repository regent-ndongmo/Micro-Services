package com.microservice.order.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commandes")
public class Order extends BaseEntity{

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "product_id", nullable = false)
    private Long product_id;
}

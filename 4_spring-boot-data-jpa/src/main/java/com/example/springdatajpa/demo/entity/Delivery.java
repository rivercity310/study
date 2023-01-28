package com.example.springdatajpa.demo.entity;

import com.example.springdatajpa.demo.entity.status.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    private DeliveryStatus status;
}

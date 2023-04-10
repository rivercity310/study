package com.example.tmp.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DeliveryLog> logs = new ArrayList<>();

    @Builder
    public Delivery(Address address) {
        this.address = address;
    }

    public void addLog(DeliveryStatus status) {
        DeliveryLog log = DeliveryLog.builder().status(status).delivery(this).build();
        logs.add(log);
    }
}

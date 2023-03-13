package com.example.tmp.domain;

import com.example.tmp.exception.DeliveryAlreadyDeliveringException;
import com.example.tmp.exception.DeliveryStatusEqualsException;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DeliveryLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private DeliveryStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false, updatable = false)
    private Delivery delivery;

    @Embedded @Setter
    private DeliveryDateTime dateTime;

    @Transient
    private DeliveryStatus lastStatus;

    @Builder
    public DeliveryLog(DeliveryStatus status, Delivery delivery) {
        verifyStatus(status, delivery);
        setStatus(status);
        this.delivery = delivery;
    }

    private void setStatus(DeliveryStatus status) {
        switch (status) {
            case DELIVERING -> delivering();
            case COMPLETED -> completed();
            case CANCELED -> cancel();
            case PENDING -> pending();
            default -> throw new IllegalArgumentException(status.name() + " is not found");
        }
    }

    private void verifyStatus(DeliveryStatus status, Delivery delivery) {
        if (!delivery.getLogs().isEmpty()) {
            lastStatus = getLastStatus(delivery);
            verifyLastStatusEquals(status);
            verifyAlreadyCompleted();
        }
    }

    private DeliveryStatus getLastStatus(Delivery delivery) {
        int lastIndex = delivery.getLogs().size() - 1;
        return delivery.getLogs().get(lastIndex).getStatus();
    }

    private void verifyLastStatusEquals(DeliveryStatus status) {
        if (lastStatus == status)
            throw new DeliveryStatusEqualsException(lastStatus);
    }

    private void verifyAlreadyCompleted() {
        if (lastStatus == DeliveryStatus.COMPLETED)
            throw new IllegalArgumentException("It has already been completed");
    }

    private void delivering() {
        this.status = DeliveryStatus.DELIVERING;
    }

    private void completed() {
        this.status = DeliveryStatus.COMPLETED;
    }

    private void cancel() {
        verifyNotYetDelivering();
        this.status = DeliveryStatus.CANCELED;
    }

    private void pending() {
        this.status = DeliveryStatus.PENDING;
    }

    private void verifyNotYetDelivering() {
        if (this.lastStatus != DeliveryStatus.PENDING)
            throw new DeliveryAlreadyDeliveringException();
    }
}

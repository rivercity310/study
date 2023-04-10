package com.example.tmp.service;

import com.example.tmp.domain.Delivery;
import com.example.tmp.domain.DeliveryStatus;
import com.example.tmp.dto.DeliveryDto;
import com.example.tmp.exception.DeliveryNotFoundException;
import com.example.tmp.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public Delivery create(DeliveryDto.CreationReq dto) {
        Delivery delivery = dto.toEntity();
        delivery.addLog(DeliveryStatus.PENDING);
        return deliveryRepository.save(delivery);
    }

    public Delivery updateStatus(Long id, DeliveryDto.UpdateReq dto) {
        Delivery delivery = findById(id);
        delivery.addLog(dto.getStatus());
        return delivery;
    }

    @Transactional(readOnly = true)
    public Delivery findById(Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        delivery.orElseThrow(() -> new DeliveryNotFoundException(id));
        return delivery.get();
    }

    public Delivery removeLogs(Long id) {
        Delivery delivery = findById(id);
        delivery.getLogs().clear();
        return delivery;
    }

    public void remove(Long id) {
        deliveryRepository.deleteById(id);
    }
}

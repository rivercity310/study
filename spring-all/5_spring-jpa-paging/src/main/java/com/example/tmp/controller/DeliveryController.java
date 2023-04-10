package com.example.tmp.controller;

import com.example.tmp.dto.DeliveryDto;
import com.example.tmp.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public DeliveryDto.Res create(@RequestBody @Valid final DeliveryDto.CreationReq dto) {
        return new DeliveryDto.Res(deliveryService.create(dto));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public DeliveryDto.Res getDelivery(@PathVariable final Long id) {
        return new DeliveryDto.Res(deliveryService.findById(id));
    }

    @PostMapping("/{id}/logs")
    @ResponseStatus(value = HttpStatus.OK)
    public DeliveryDto.Res updateDelivery(
            @PathVariable final Long id,
            @RequestBody DeliveryDto.UpdateReq dto
    ) {
        return new DeliveryDto.Res(deliveryService.updateStatus(id, dto));
    }
}

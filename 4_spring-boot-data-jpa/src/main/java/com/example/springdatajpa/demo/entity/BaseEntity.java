package com.example.springdatajpa.demo.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
    private String createBy;
    private String modifiedBy;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}

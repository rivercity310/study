package com.example.dsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Hello {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

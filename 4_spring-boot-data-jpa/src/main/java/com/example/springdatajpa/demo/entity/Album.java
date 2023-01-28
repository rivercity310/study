package com.example.springdatajpa.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue(value = "album")
public class Album extends Item {
    private String artist;
    private String etc;
}

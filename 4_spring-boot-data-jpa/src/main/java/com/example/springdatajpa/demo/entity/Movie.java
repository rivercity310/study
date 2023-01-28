package com.example.springdatajpa.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue(value = "movie")
public class Movie extends Item {
    private String director;
    private String actor;
}

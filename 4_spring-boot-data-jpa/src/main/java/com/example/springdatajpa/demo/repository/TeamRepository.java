package com.example.springdatajpa.demo.repository;

import com.example.springdatajpa.demo.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> { }

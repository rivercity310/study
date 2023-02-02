package com.example.springdatajpa.demo.repository;

import com.example.springdatajpa.demo.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired ItemRepository itemRepository;

    @Test
    public void save() {
        Item item = new Item();
        itemRepository.save(item);
    }
}
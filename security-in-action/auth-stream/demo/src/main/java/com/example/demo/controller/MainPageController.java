package com.example.demo.controller;

import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainPageController {
    private final ProductRepository productRepository;

    @GetMapping("/main")
    public String home(Authentication e, Model model) {
        model.addAttribute("username", e.getName());
        model.addAttribute("products", productRepository.findAll());
        return "main";
    }
}

package com.example.thingsFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ThingsFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThingsFlowApplication.class, args);
	}

}


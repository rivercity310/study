package com.example.restapi

import com.example.restapi.domain.Address
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestapiApplication

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}

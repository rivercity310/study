package com.example.gameduo.controller

import com.example.gameduo.dto.UserDTO
import com.example.gameduo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/user")
    internal fun createUser(): UserDTO =
        userService.createUser()

    @GetMapping("/user/{userId}")
    internal fun getUser(@PathVariable("userId") userId: Long): UserDTO =
        userService.getUser(userId)
}
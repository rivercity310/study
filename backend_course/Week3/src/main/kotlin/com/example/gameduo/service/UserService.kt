package com.example.gameduo.service

import com.example.gameduo.dto.UserDTO
import com.example.gameduo.entity.User
import com.example.gameduo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {
    @Transactional
    internal fun createUser(): UserDTO {
        val userId: Long = userRepository.createUser(User())
        return UserDTO(userId = userId)
    }

    @Transactional
    internal fun getUser(userId: Long): UserDTO {
        val user: User = userRepository.getUser(userId)
        if (user.userId == -1L) return UserDTO(userId = -1, errorMsg = "존재하지 않는 user")
        return UserDTO(totalScore = user.getTotalScore(), bossRaidHistory = user.getBossRaidDTOHistory())
    }
}
package com.example.gameduo.controller

import com.example.gameduo.dto.UserDTO
import com.example.gameduo.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserControllerTest @Autowired constructor(private val userService: UserService) {
    @Test
    @DisplayName("유저가 정상적으로 생성되고 조회되는지 테스트")
    internal fun createUserAndGetUserTest() {
        val createdUserDTO = userService.createUser()
        val createdUserId = createdUserDTO.userId!!
        val findUserDTO: UserDTO = userService.getUser(createdUserId)

        /* 두 DTO의 히스토리가 동일해야 함 */
        for (i in 0 until findUserDTO.bossRaidHistory.size)
            if (findUserDTO.bossRaidHistory[i] != createdUserDTO.bossRaidHistory[i])
                fail<String>("보스레이드 히스토리가 같아야 함")

        /* totalScore 검증 -> 초기 null, db 초기화 후 0 */
        assertEquals(createdUserDTO.totalScore, null)
        assertEquals(findUserDTO.totalScore, 0)
    }
}
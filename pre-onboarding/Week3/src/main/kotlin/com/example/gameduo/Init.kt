package com.example.gameduo

import com.example.gameduo.service.BossRaidService
import com.example.gameduo.service.UserService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/*
 * [ 로딩시 자동으로 더미 데이터를 추가해주는 클래스 ]
 * -> 유저 2명 생성
 * -> 유저 1은 레이드에 입장
 * */
@Component
class Init(private val initService: InitService) {
    @PostConstruct
    internal fun initDB() =
        initService.insertDummies()

    @Component
    class InitService @Autowired constructor(
        private val userService: UserService,
        private val bossRaidService: BossRaidService
    ) {
        @Transactional
        internal fun insertDummies() {
            val user1 = userService.createUser()
            val user2 = userService.createUser()
            bossRaidService.startRaid(user1.userId, 2L)
        }
    }
}
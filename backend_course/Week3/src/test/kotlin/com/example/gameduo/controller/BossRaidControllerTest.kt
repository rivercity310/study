package com.example.gameduo.controller

import com.example.gameduo.dto.BossRaidDTO
import com.example.gameduo.dto.RaidStatusDTO
import com.example.gameduo.dto.UserDTO
import com.example.gameduo.service.BossRaidService
import com.example.gameduo.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BossRaidControllerTest @Autowired constructor(
    private val bossRaidService: BossRaidService,
    private val userService: UserService
) {

    @Test
    @DisplayName("유저 생성 & 레이드 시작, 종료 & 레이드 상태 조회 & 예외 작동 테스트")
    internal fun raidAllTest() {
        /* 이미 레이드를 시작한 다른 유저가 존재하면 입장 불가 */
        val newUser: UserDTO = userService.createUser()
        val raidStartDTO: BossRaidDTO = bossRaidService.startRaid(newUser.userId, 1L) /* 입장에 실패해야 함 */

        Assertions.assertEquals(raidStartDTO.errorMsg, "이미 다른 사용자가 레이드를 시작하였습니다")
        Assertions.assertEquals(raidStartDTO.isEntered, false)

        finishRaidTest()

        /* 레이드 상태 조회 1 -> 레이드 입장이 가능한 경우 */
        var raidStatus: RaidStatusDTO = bossRaidService.getRaidStatus()
        Assertions.assertEquals(raidStatus.canEnter, true)

        /* 1번 유저의 레이드를 종료시킨 뒤 2번 유저가 레이드 진행 -> 레이드 입장에 성공해야 함 */
        val newRaidStartDTO: BossRaidDTO = bossRaidService.startRaid(2L, 1L)
        Assertions.assertEquals(newRaidStartDTO.raidRecordId, 2L)
        Assertions.assertEquals(newRaidStartDTO.isEntered, true)

        /* 레이드 상태 조회 2 -> 레이드 입장이 불가한 경우 */
        val newRaidStartDTO2: BossRaidDTO = bossRaidService.startRaid(1L, 2L)
        Assertions.assertEquals(newRaidStartDTO2.isEntered, false)
        Assertions.assertEquals(newRaidStartDTO2.errorMsg, "이미 다른 사용자가 레이드를 시작하였습니다")

        /* 최종 진행중인 레이드 종료 */
        bossRaidService.finishRaid(newRaidStartDTO.userId, newRaidStartDTO.raidRecordId)
    }

    private fun finishRaidTest() =
        bossRaidService.finishRaid(1L, 1L)
}
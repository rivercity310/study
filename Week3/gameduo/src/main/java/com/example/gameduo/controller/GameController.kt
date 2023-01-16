package com.example.gameduo.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@Api(tags = ["Main Controller"])
@RestController
open class GameController {
    @ApiOperation(value = "유저를 생성합니다")
    @PostMapping("/user")
    fun createUser(): Int? {
        return null
    }

    @ApiOperation(value = "유저를 조회합니다")
    @GetMapping("/user")
    fun getUser(@RequestParam("userId") userId: Int?) {
    }

    @ApiOperation(value = "보스레이드 상태를 조회합니다")
    @GetMapping("/bossRaid")
    fun getRaidStatus() {
    }

    @ApiOperation(value = "보스레이드를 시작합니다")
    @PostMapping("/bossRaid/enter")
    fun startRaid(@RequestBody tmp: Int?) {
    }

    @ApiOperation(value = "보스레이드를 종료합니다")
    @PatchMapping("/bossRaid/end")
    fun finishRaid(@RequestBody tmp: Int?) {
    }

    @ApiOperation(value = "보스레이드 랭킹을 조회합니다")
    @GetMapping("/bossRaid/topRankerList")
    fun getRankerList() {
    }
}
package com.example.gameduo.caching

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CachingStaticDataTest @Autowired constructor(private val cachingStaticData: CachingStaticData) {
    @Test
    @DisplayName("캐싱 기능 테스트 1")
    internal fun getLimitSecondsFirst() {
        val limitSeconds = cachingStaticData.getBossRaidLimitSeconds()
        Assertions.assertEquals(limitSeconds, 180)
    }

    @Test
    @DisplayName("캐싱 기능 테스트 2")
    @Disabled
    internal fun getLimitSecondsSecond() {
        val limitSeconds = cachingStaticData.getBossRaidLimitSeconds()
        println("$limitSeconds")
        Assertions.assertEquals(limitSeconds, 180)
    }

    @Test
    @DisplayName("값을 제대로 가져오는지 테스트")
    internal fun getLevels() {
        val levels = cachingStaticData.getLevels()
        levels.forEach { (key, value) -> println("$key : $value") }
    }
}
package com.example.gameduo.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BossStaticAPIParserTest {
    private val bossStaticAPIParser = BossStaticAPIParser

    @Test
    @DisplayName("외부 스태틱 데이터를 정상적으로 가져오는지 테스트")
    internal fun getStaticData() {
        val staticData = bossStaticAPIParser.getStaticData()

        val bossRaidLimitSeconds: Long = staticData.bossRaidLimitSeconds!!
        val levels: Map<Long, Long> = staticData.levels

        Assertions.assertEquals(bossRaidLimitSeconds, 180)
        Assertions.assertEquals(levels.size, 3)

        val keys: List<Long> = listOf(0, 1, 2)
        val values: List<Long> = listOf(20, 47, 85)

        for (i in 0 until levels.size) {
            val value = levels[keys[i]]
            Assertions.assertEquals(values[i], value)
        }
    }
}
package com.example.gameduo.caching

import com.example.gameduo.parser.StaticDataAPIParser
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
class CachingStaticData {
    private val staticDataAPIParser = StaticDataAPIParser
    private val staticData = staticDataAPIParser.getStaticData()

    @Cacheable("bossRaidLimitSeconds")
    internal fun getBossRaidLimitSeconds() = staticData.bossRaidLimitSeconds

    @Cacheable("levels")
    internal fun getLevels() = staticData.levels
}
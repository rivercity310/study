package com.example.gameduo.dto

data class StaticDataDTO(
    var bossRaidLimitSeconds: Long? = null,
    var levels: Map<Long, Long> = hashMapOf()
)
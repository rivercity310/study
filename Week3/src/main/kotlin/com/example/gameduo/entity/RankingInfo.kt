package com.example.gameduo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("rankingInfo")
data class RankingInfo(
    @Id @JsonIgnore
    val id: String? = null,
    var ranking: Long? = null,
    var userId: Long? = null,
    var totalScore: Long? = null
)

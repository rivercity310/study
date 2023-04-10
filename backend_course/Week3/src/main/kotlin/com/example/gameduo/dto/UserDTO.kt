package com.example.gameduo.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserDTO(
    val userId: Long? = null,
    var level: Long? = null,
    var totalScore: Long? = null,
    var bossRaidHistory: List<BossRaidDTO> = arrayListOf(),
    var errorMsg: String? = null
)

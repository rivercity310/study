package com.example.gameduo.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class BossRaidDTO(
    var raidRecordId: Long? = null,
    var isEntered: Boolean? = null,
    var score: Long? = null,
    var userId: Long? = null,
    var enterTime: LocalDateTime? = null,
    var endTime: LocalDateTime? = null,
    val errorMsg: String? = null
)
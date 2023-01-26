package com.example.gameduo.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class RaidStatusDTO(
    val canEnter: Boolean? = null,
    val enteredUserId: Long? = null
)
package org.example.mvc.handlerMapping

import org.example.mvc.RequestMethod

data class HandlerKey(
    val path: String,
    val method: RequestMethod
)
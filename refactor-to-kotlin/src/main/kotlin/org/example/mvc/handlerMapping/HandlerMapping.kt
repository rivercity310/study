package org.example.mvc.handlerMapping

interface HandlerMapping {
    fun findHandler(key: HandlerKey) : Any
}
package org.example.mvc.handlerMapping

import org.example.mvc.RequestMethod
import org.example.mvc.controller.Controller
import org.example.mvc.controller.HomeController

class RequestMappingHandlerMapping : HandlerMapping {
    private val handlers : MutableMap<HandlerKey, Controller> = mutableMapOf()

    fun init() {
        handlers[HandlerKey("/", RequestMethod.GET)] = HomeController()
    }

    override fun findHandler(key: HandlerKey): Controller {
        return handlers[key]!!
    }
}
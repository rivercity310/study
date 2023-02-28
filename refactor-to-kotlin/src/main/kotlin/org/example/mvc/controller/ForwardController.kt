package org.example.mvc.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ForwardController(private val forwardPath: String) : Controller {
    override fun handleRequest(req: HttpServletRequest, resp: HttpServletResponse): String {
        return forwardPath
    }
}
package org.example.mvc.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface Controller {
    fun handleRequest(req: HttpServletRequest, resp: HttpServletResponse): String
}
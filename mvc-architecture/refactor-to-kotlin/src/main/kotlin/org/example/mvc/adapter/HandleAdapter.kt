package org.example.mvc.adapter

import org.example.mvc.view.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface HandleAdapter {
    fun supports(handler: Any): Boolean
    fun handle(req: HttpServletRequest, resp: HttpServletResponse, handler: Any): ModelAndView
}
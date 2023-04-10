package org.example.controller

import org.example.annotation.Controller
import org.example.annotation.RequestMapping
import org.example.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
open class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    internal fun home(request: HttpServletRequest, response: HttpServletResponse): String {
        return "home"
    }
}
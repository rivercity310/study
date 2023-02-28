package org.example.mvc.controller

import org.example.mvc.RequestMethod
import org.example.mvc.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HomeController : Controller {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    override fun handleRequest(req: HttpServletRequest, resp: HttpServletResponse): String {
        return "home"
    }
}
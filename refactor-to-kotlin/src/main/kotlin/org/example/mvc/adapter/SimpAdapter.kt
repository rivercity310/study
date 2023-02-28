package org.example.mvc.adapter

import org.example.mvc.controller.Controller
import org.example.mvc.view.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SimpAdapter : HandleAdapter {
    override fun supports(handler: Any): Boolean {
        return handler is Controller
    }

    override fun handle(req: HttpServletRequest, resp: HttpServletResponse, handler: Any): ModelAndView {
        val controller = handler as Controller
        val viewName = controller.handleRequest(req, resp)

        return ModelAndView(viewName)
    }
}
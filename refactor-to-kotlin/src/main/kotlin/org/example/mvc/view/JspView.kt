package org.example.mvc.view

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JspView(private val viewName: String) : View {
    override fun render(model: Map<String, *>, req: HttpServletRequest, resp: HttpServletResponse) {
        model.forEach(req::setAttribute)

        val requestDispatcher = req.getRequestDispatcher(viewName)
        requestDispatcher.forward(req, resp)
    }
}
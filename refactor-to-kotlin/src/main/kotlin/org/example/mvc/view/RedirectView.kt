package org.example.mvc.view

import org.example.mvc.view.resolver.DEFAULT_REDIRECT_PREFIX
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RedirectView(private val viewName: String) : View {
    override fun render(model: Map<String, *>, req: HttpServletRequest, resp: HttpServletResponse) {
        val path = viewName.substring(DEFAULT_REDIRECT_PREFIX.length)
        resp.sendRedirect(path)
    }
}
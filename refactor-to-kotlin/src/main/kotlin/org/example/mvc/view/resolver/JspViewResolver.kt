package org.example.mvc.view.resolver

import org.example.mvc.view.JspView
import org.example.mvc.view.RedirectView
import org.example.mvc.view.View

const val DEFAULT_REDIRECT_PREFIX = "redirect:"

class JspViewResolver : ViewResolver {
    override fun resolveView(viewName: String): View {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            return RedirectView(viewName)
        }

        return JspView("${viewName}.jsp")
    }
}
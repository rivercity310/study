package org.example.mvc.view.resolver

import org.example.mvc.view.View

interface ViewResolver {
    fun resolveView(viewName: String) : View
}
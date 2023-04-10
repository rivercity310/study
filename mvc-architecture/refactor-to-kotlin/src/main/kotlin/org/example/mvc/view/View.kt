package org.example.mvc.view

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface View {
    fun render(model: Map<String, *>, req: HttpServletRequest, resp: HttpServletResponse)
}
package org.example.mvc

import org.example.mvc.adapter.HandleAdapter
import org.example.mvc.adapter.SimpAdapter
import org.example.mvc.handlerMapping.HandlerKey
import org.example.mvc.handlerMapping.HandlerMapping
import org.example.mvc.handlerMapping.RequestMappingHandlerMapping
import org.example.mvc.view.View
import org.example.mvc.view.resolver.JspViewResolver
import org.example.mvc.view.resolver.ViewResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/")
class DispatcherServlet : HttpServlet() {
    private lateinit var log : Logger
    private lateinit var handlerMappings : List<HandlerMapping>
    private lateinit var handleAdapters : List<HandleAdapter>
    private lateinit var viewResolvers : List<ViewResolver>

    override fun init() {
        log = LoggerFactory.getLogger(DispatcherServlet::class.java)
        log.debug("init()")

        val requestMappingHandlerMapping = RequestMappingHandlerMapping()
        requestMappingHandlerMapping.init()


        handlerMappings = listOf(requestMappingHandlerMapping)
        handleAdapters = listOf(SimpAdapter())
        viewResolvers = listOf(JspViewResolver())
    }

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        log.debug("service()")

        val requestURI = req.requestURI
        val method = RequestMethod.valueOf(req.method)
        val handlerKey = HandlerKey(requestURI, method)

        val handler = handlerMappings
            .first { it.findHandler(handlerKey) != null }
            .findHandler(handlerKey)

        val adapter = handleAdapters.first { it.supports(handler) }
        val modelAndView = adapter.handle(req, resp, handler)

        for (viewResolver in viewResolvers) {
            val view : View = viewResolver.resolveView(modelAndView.getViewName()!!)
            view.render(modelAndView.model, req, resp)
        }
    }
}
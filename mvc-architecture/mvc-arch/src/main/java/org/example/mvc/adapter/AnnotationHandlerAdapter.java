package org.example.mvc.adapter;

import org.example.mvc.modelAndView.view.ModelAndView;
import org.example.mvc.handlerMapping.AnnotationHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AnnotationHandler hdr = (AnnotationHandler) handler;
        String viewName = hdr.handle(request, response);

        return new ModelAndView(viewName);
    }
}

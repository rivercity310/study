package org.example.mvc;

import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.handlerMapping.AnnotationHandler;
import org.example.mvc.handlerMapping.HandlerMapping;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {
    private final Object[] basePackage;
    private final Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();
    private final Logger log = LoggerFactory.getLogger(AnnotationHandlerMapping.class);

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(org.example.mvc.annotation.Controller.class, true);

        log.info(clazzesWithControllerAnnotation.toString());

        clazzesWithControllerAnnotation.forEach(clazz ->

                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    Arrays.stream(getRequestMethods(requestMappingAnnotation))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMappingAnnotation.value()),
                                    new AnnotationHandler(clazz, declaredMethod)
                            ));

                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMappingAnnotation) {
        return requestMappingAnnotation.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
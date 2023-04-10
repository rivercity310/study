package org.example.mvc.handlerMapping;


import org.example.mvc.controller.HandlerKey;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}

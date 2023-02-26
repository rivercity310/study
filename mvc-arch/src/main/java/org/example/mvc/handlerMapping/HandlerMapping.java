package org.example.mvc.handlerMapping;


import org.example.mvc.HandlerKey;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}

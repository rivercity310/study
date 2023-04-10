package org.example.mvc.handlerMapping;

import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
    private final Map<HandlerKey, Controller> mappings = new HashMap<>();

    public void init() {
        // mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        // mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        // mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey key) {
        return mappings.get(key);
    }
}

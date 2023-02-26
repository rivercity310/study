package org.example.mvc;

import java.util.Objects;

public class HandlerKey {
    private final RequestMethod requestMethod;
    private final String path;

    public HandlerKey(RequestMethod requestMethod, String path) {
        this.requestMethod = requestMethod;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return requestMethod == that.requestMethod && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestMethod, path);
    }
}

package org.example.mvc.di;

import java.lang.reflect.Constructor;
import java.util.*;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClass;
    private final Map<Class<?>, Object> beans;

    public BeanFactory(Set<Class<?>> preInstantiatedClass) {
        this.beans = new HashMap<>();
        this.preInstantiatedClass = preInstantiatedClass;

        initialize();
    }

    private void initialize() {
        for (Class<?> cls : preInstantiatedClass) {
            Object instance = createInstance(cls);
            beans.put(cls, instance);
        }
    }

    private Object createInstance(Class<?> cls) {
        Constructor constructor = findConstructor(cls);
        List<Object> params = new ArrayList<>();

        for (Class<?> typeClass : constructor.getParameterTypes()) {

        }
    }

    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}

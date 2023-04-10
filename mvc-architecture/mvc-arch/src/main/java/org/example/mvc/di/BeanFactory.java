package org.example.mvc.di;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        Constructor<?> constructor = findConstructor(cls);
        List<Object> params = new ArrayList<>();

        // assert constructor != null; : AssertionError Occurred When constructor is null

        for (Class<?> typeClass : constructor.getParameterTypes()) {
            params.add(getParameterByClass(typeClass));
        }

        try {
            return constructor.newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> cls) {
        Constructor<?> injectedConstructor = BeanFactoryUtils.getConstructor(cls);

        if (Objects.nonNull(injectedConstructor))
            return injectedConstructor;

        return cls.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> cls) {
        Object instanceBean = getBean(cls);

        if (Objects.nonNull(instanceBean))
            return instanceBean;

        return createInstance(cls);
    }

    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}

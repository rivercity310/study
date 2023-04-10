package org.example.mvc.di;

import org.example.mvc.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    public static Constructor<?> getConstructor(Class<?> cls) {
        Set<Constructor> injectedConstructors =
                ReflectionUtils.getAllConstructors(cls, ReflectionUtils.withAnnotation(Inject.class));

        if (injectedConstructors.isEmpty())
            return null;

        return injectedConstructors.iterator().next();
    }
}

package org.example.di;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.Repository;
import org.example.mvc.annotation.Service;
import org.example.mvc.controller.UserController;
import org.example.mvc.di.BeanFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        Set<Class<?>> preInstantiatedClass = annotatedWith(Controller.class, Service.class, Repository.class);

        beanFactory = new BeanFactory(preInstantiatedClass);
    }

    @SafeVarargs
    private Set<Class<?>> annotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();

        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.userService()).isNotNull();
        assertThat(userController.userService().getUserRepository()).isNotNull();
    }
}

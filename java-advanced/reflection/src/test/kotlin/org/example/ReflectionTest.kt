package org.example

import org.assertj.core.api.Assertions.*
import org.example.annotation.Controller
import org.example.annotation.Service
import org.example.model.User
import org.junit.jupiter.api.Test
import org.reflections.Reflections
import org.slf4j.LoggerFactory

// @Controller 어노테이션이 설정된 모든 클래스를 찾아서 출력
class ReflectionTest {
    private val logger = LoggerFactory.getLogger(ReflectionTest::class.java)

    @Test
    fun componentScanTest() {
        val lst = listOf(Controller::class.java, Service::class.java)
        val reflections = Reflections("org.example")

        val beans : MutableSet<Any> = hashSetOf()

        lst.forEach {
            beans.addAll(reflections.getTypesAnnotatedWith(it))
        }

        logger.debug("[ ReflectionTest : {}]", beans)
    }

    @Test
    fun showClassTest() {
        val clazz = User::class.java

        logger.debug(clazz.name)
        logger.debug("user all declared fields : [{}]", clazz.declaredFields)
        logger.debug("user all declared constructs : [{}]", clazz.declaredConstructors)
        logger.debug("user all declared method : [{}]", clazz.declaredMethods)
    }

    @Test
    fun loadTest() {
        // 1
        val clazz : Class<User> = User::class.java

        // 2
        val user = User("aa", "seungsu")
        val clazz2 = user.javaClass

        // 3
        val clazz3 = Class.forName("org.example.model.User")

        logger.debug("class : [{}]", clazz)
        logger.debug("class2 : [{}]", clazz2)
        logger.debug("class3 : [{}]", clazz3)

        assertThat(clazz == clazz2).isTrue
        assertThat(clazz == clazz3).isTrue
        assertThat(clazz2 == clazz3).isTrue
    }
}
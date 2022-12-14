package com.example.kotlin_spring.polymorphism

import com.example.kotlin_spring.polymorphism.tv.TV
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext

class TVUser {}
fun main(args: Array<String>) {
    val factory: AbstractApplicationContext =
        GenericXmlApplicationContext("applicationContext.xml")

    val tv: TV = factory.getBean("lgTV") as TV

    tv.powerOn()
    tv.volumeUp()
    tv.volumeDown()
    tv.powerOff()

    factory.close()
}



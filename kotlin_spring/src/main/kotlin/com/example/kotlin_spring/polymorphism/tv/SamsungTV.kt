package com.example.kotlin_spring.polymorphism.tv

import com.example.kotlin_spring.polymorphism.speaker.Speaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.properties.Delegates

class SamsungTV(private val speaker: Speaker, private val price: Int = 2500000) : TV {
    fun initMethod() {
        println("SamsungTV -- Init")
    }

    fun destroyMethod() {
        println("SamsungTV -- Destroy")
    }

    init {
        println("SamsungTV 객체 생성, price = $price")
    }

    override fun powerOn() {
        println("SamsungTV --- On")
    }

    override fun powerOff() {
        println("SamsungTV --- Off")
    }

    override fun volumeUp() {
        speaker.volumeUp()
    }

    override fun volumeDown() {
        speaker.volumeDown()
    }
}
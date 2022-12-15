package com.example.kotlin_spring.polymorphism.tv

import com.example.kotlin_spring.polymorphism.speaker.Speaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.stereotype.Component

@Component
class LgTV: TV {
    private lateinit var speaker: Speaker       // 필드 주입
    private var price = 2000000

    init {
        println("LgTV 객체 생성, price = $price")
    }

    fun setSpeaker(speaker: Speaker) {
        this.speaker = speaker
        println("LgTV -- setSpeaker 호출")
    }

    fun setPrice(price: Int) {
        this.price = price
        println("LgTV의 price -> $price")
    }

    override fun powerOn() {
        println("LgTV --- On")
    }

    override fun powerOff() {
        println("LgTV --- Off")
    }

    override fun volumeUp() {
        speaker.volumeUp()
    }

    override fun volumeDown() {
        speaker.volumeDown()
    }
}
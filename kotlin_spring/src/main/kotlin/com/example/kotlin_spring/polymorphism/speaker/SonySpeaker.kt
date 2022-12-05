package com.example.kotlin_spring.polymorphism.speaker

class SonySpeaker: Speaker {

    init {
        println("SonySpeaker 객체 생성..")
    }

    override fun volumeUp() {
        println("SonySpeaker Volume Up")
    }

    override fun volumeDown() {
        println("SonySpeaker Volume Down")
    }
}
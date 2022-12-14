package com.example.kotlin_spring.polymorphism.speaker

class AppleSpeaker: Speaker {

    init {
        println("AppleSpeaker 객체 생성..")
    }

    override fun volumeUp() {
        println("AppleSpeaker Volume Up")
    }

    override fun volumeDown() {
        println("AppleSpeaker Volume Down")
    }
}
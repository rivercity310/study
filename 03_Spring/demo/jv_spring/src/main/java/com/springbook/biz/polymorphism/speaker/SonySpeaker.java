package com.springbook.biz.polymorphism.speaker;

public class SonySpeaker implements Speaker {
    public SonySpeaker() {
        System.out.println("===> SonySpeaker 객체 생성");
    }

    public void volumeUp() {
        System.out.println("SonySpeaker Volume Up");
    }

    public void volumeDown() {
        System.out.println("SonySpeaker Volume Down");
    }
}

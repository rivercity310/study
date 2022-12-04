package com.example.java_spring.polymorphism.tv;

import com.example.java_spring.polymorphism.speaker.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SamsungTV implements TV {
    @Autowired
    private Speaker speaker;
    private int price;

    public SamsungTV() {
        System.out.println("===> SamsungTV(default constructor) 객체 생성");
    }

    /*
    public SamsungTV(Speaker speaker) {
        System.out.println("===> SamsungTV(constructor overload first) 객체 생성");
        this.speaker = speaker;
    }

    public SamsungTV(Speaker speaker, int price) {
        System.out.println("===> SamsungTV(constructor overload second) 객체 생성");
        this.speaker = speaker;
        this.price = price;
    }
    */

    public void initMethod() {
        System.out.println("SamsungTV -- 객체 초기화 작업 처리..");
    }

    public void destroyMethod() {
        System.out.println("SamsungTV -- 객체 삭제 전 처리..");
    }

    @Override
    public void powerOn() {
        System.out.println("SamsungTV -- Power on");
    }

    @Override
    public void powerOff() {
        System.out.println("SamsungTV -- Power off");
    }

    @Override
    public void volumeUp() {
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        speaker.volumeDown();
    }
}

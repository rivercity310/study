package com.springbook.biz.polymorphism.tv;

import com.springbook.biz.polymorphism.speaker.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LgTV implements TV {
    private Speaker speaker;
    private int price;

    public LgTV() {
        System.out.println("===> LgTV 객체 생성");
    }

    /*
    public LgTV(Speaker speaker) {
        this.speaker = speaker;
        System.out.println("===> LgTV 객체 생성");
    }

    public void setSpeaker(Speaker speaker) {
        System.out.println("===> setSpeaker() 호출");
        this.speaker = speaker;
    }

    public void setPrice(int price) {
        System.out.println("===> setPrice() 호출");
        this.price = price;
    }
    */

    @Override
    public void powerOn() {
        System.out.println("LgTV -- Turn on, price " + price);
    }

    @Override
    public void powerOff() {
        System.out.println("LgTV -- Turn off");
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

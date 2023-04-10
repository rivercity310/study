package com.example.springwebsocketstomp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* 메세지 스펙 정의 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String type;
    private String sender;
    private String channelId;
    private Object data;

    public void setSender(String sender) { this.sender = sender; }

    public void newConnect() { this.type = "new"; }

    public void closeConnect() { this.type = "close"; }
}

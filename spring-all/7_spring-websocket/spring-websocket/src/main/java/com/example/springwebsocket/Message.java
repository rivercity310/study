package com.example.springwebsocket;

import lombok.*;

/**
 * - 서버, 클라이언트 간 소켓 통신에서 사용할 스펙 정의
 * - { "type": "", "sender": "me", "receiver": "..", "data": "test..." }
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String type;
    private String sender;
    private String receiver;
    private Object data;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void newConnect() {
        this.type = "new";
    }

    public void closeConnect() {
        this.type = "close";
    }
}

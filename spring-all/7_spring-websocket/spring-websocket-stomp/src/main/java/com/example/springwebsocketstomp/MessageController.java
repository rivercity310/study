package com.example.springwebsocketstomp;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
         /sub/channel/12345    - 구독 (channelId: 12345)
         /pub/hello            - 메세지 발행
     */

    @MessageMapping("/hello")       /* 클라이언트에서 (/pub/hello)로 메세지를 발행한다. */
    public void message(Message message) {
        /* 메세지에 정의된 채널 Id에 메세지를 보낸다
        *       -> /sub/channel/{channelId}를 구독중인 클라이언트에게 메세지 전송
        * */
        simpMessageSendingOperations
                .convertAndSend("/sub/channel/" + message.getChannelId(), message);
    }
}

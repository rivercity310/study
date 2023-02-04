package com.example.springwebsocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** [웹소켓 핸들러는 다음 4개의 메서드를 오버라이드 해야함]
 * 1. afterConnectionEstablished: 웹소켓 연결 시
 * 2. handleTextMessage: 데이터 통신 시
 * 3. afterConnectionClosed: 웹소켓 연결 종료 시
 * 4. handleTransportError: 웹소켓 통신 에러 시
 */

public class WebSocketHandler extends TextWebSocketHandler {
    /* 기존 접속자의 웹소켓 세션 정보 보관 (세션 ID : 세션)*/
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /* 접속중인 모든 세션에 입장 알림을 보내는 기능 추가 */
        String sessionId = session.getId();
        sessions.put(sessionId, session);   // 세션 저장

        System.out.println("sessionId = " + sessionId);

        Message message = Message.builder()
                .sender(sessionId)
                .receiver("all")
                .build();
        message.newConnect();

        // 자신을 제외한 모든 세션에 알림
        sessions.values().forEach(s -> {
            try {
                if (!s.getId().equals(sessionId))
                    s.sendMessage(new TextMessage(Utils.getString(message)));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        /* 양방향 데이터 통신 로직 구현 */
        Message message = Utils.getObject(textMessage.getPayload());
        message.setSender(session.getId());

        WebSocketSession receiver = sessions.get(message.getReceiver());

        /* 메세지를 전달할 타겟 찾기 */
        if (receiver != null && receiver.isOpen())
            receiver.sendMessage(new TextMessage(Utils.getString(message)));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessions.remove(sessionId);     /* 연결이 끊긴 사용자 삭제 */

        final Message message = new Message();
        message.closeConnect();
        message.setSender(sessionId);

        sessions.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(Utils.getString(message)));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

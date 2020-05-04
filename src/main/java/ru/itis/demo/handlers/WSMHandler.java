package ru.itis.demo.handlers;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.ls.LSOutput;
import ru.itis.demo.dto.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WSMHandler extends TextWebSocketHandler {
    private static Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        System.out.println();
        String messageText = (String) message.getPayload();
        Message messageFromJson = objectMapper.readValue(messageText, Message.class);

        if (!sessions.containsKey(messageFromJson.getFrom())) {
            sessions.put(messageFromJson.getFrom(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            currentSession.sendMessage(message);
        }

    }
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session){
//        sessions.put(session.getId(),session);
//    }
//
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        session.close();
        for (String curr: sessions.keySet()) {
            if (sessions.get(curr).equals(session)){
                sessions.remove(curr);
            }
        }
    }

}

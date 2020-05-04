package ru.itis.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.demo.handlers.AuthHandshakeHandler;
import ru.itis.demo.handlers.WSMHandler;

@Configuration
public class WebSocketConfiguration  implements WebSocketConfigurer {


    @Autowired
    private WSMHandler wsmHandler;

    @Autowired
    private AuthHandshakeHandler authHandshakeHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(wsmHandler, "/chat1").setHandshakeHandler(authHandshakeHandler);

    }

}

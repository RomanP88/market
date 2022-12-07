package ru.gb.market.core.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //настройка веб сокет подключеня
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//регистрируем конечную точку, которую клиенты будут использовать, чтобы подключиться к нашему Websocket-серверу
        registry.addEndpoint("ws").setAllowedOrigins("http://localhost:3000").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//настраиваем брокер сообщений, который будет использоваться для направления сообщений от одного клиента к другому
        registry.enableSimpleBroker("/topic");//устанавливаем префикс для фильтрации адресатов, которые будет отслеживать брокер сообщений
        registry.setApplicationDestinationPrefixes("/app");//устанавливаем префикс по которому брокер будет отслеживать входящие сообщения, то что в аннотации контроллера @MessageMapping("/giveMeFile") будет добавляеться и получим "app/giveMeFile"
    }
}

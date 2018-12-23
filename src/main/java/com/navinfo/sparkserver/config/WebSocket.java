/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.config
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 10:49
 **************************************/
package com.navinfo.sparkserver.config;

/*************************************
 * Class Name: WebSocket
 * Description:〈websocket配置文件〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 * @author wulongyue
 */
@Configuration
//注解开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableWebSocketMessageBroker
@CrossOrigin("*")
public class WebSocket extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//注册STOMP协议的节点(endpoint),并映射指定的url
        //注册一个STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/endpointAric").setAllowedOrigins("*").withSockJS();
        //注册名为"endpointChat"的endpoint
        registry.addEndpoint("/endpointChat").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(Message Broker)
        //广播式应配置一个/topic消息代理
//      registry.enableSimpleBroker("/topic");

        //点对点式应配置/queue和/topic消息代理
        registry.enableSimpleBroker("/queue","/topic");
    }
}

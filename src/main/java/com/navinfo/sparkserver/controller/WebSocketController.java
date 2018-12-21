/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.controller
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 11:20
 **************************************/
package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.navinfo.sparkserver.model.AricMessage;
import com.navinfo.sparkserver.model.AricResponse;
import com.navinfo.sparkserver.model.ResponseMessage;
import com.navinfo.sparkserver.service.WebSocketServer;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.atomic.AtomicInteger;

/*************************************
 * Class Name: WebSocketController
 * Description:〈websocket 控制器〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/
@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    //通过simpMessagingTemplate向浏览器发送消息
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);


    /**
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     * @param requestMessage
     * @return
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public ResponseMessage broadcast(RequestMessage requestMessage){
        logger.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }

    @RequestMapping(value="/broadcast/index")
    public String broadcastIndex(HttpServletRequest req){
        System.out.println(req.getRemoteHost());
        return "websocket/simple/ws-broadcast";
    }

    @MessageMapping("/chat")
    //在springmvc中,可以直接在参数中获得principal,pinciple中包含当前用户信息
    public void handleChat(Principal principal, String msg){
        if ("james".equals(principal.getName())) {//硬编码,对用户姓名进行判断
            //向用户发送消息,第一个参数:接收消息的用户,第二个参数:浏览器订阅地址,第三个参数:消息
            simpMessagingTemplate.convertAndSendToUser("curry",
                    "/queue/notifications", principal.getName() + "-send: " + msg);
        } else {

            simpMessagingTemplate.convertAndSendToUser("james",
                    "/queue/notifications", principal.getName() + "-send: " + msg);
        }
    }

    @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public AricResponse say(AricMessage message) {
        try {
            //睡眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AricResponse("welcome," + message.getName() + "!");
    }
}

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.controller
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 11:20
 **************************************/
package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.navinfo.sparkserver.config.SocketSessionRegistry;
import com.navinfo.sparkserver.model.*;
import com.navinfo.sparkserver.service.AdasService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/*************************************
 * Class Name: WebSocketController
 * Description:〈websocket 控制器〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/
@CrossOrigin("*")
@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
//
    /**session操作类*/
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private AdasService adasService;

    @RequestMapping(value = "/index")
    public  String index(){
        return "/index";
    }
    @RequestMapping(value = "/msg/message")
    public  String ToMessage(){
        return "/message";
    }
    @RequestMapping(value = "/msg/messaget2")
    public  String ToMessaget2(){
        return "/messaget2";
    }

    /**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/hellosingle")
    public void greeting2(RequestMessage message) throws Exception {
        Map<String,String> params = new HashMap(1);
        params.put("test","test");
        //这里没做校验
        String sessionId=webAgentSessionRegistry.getSessionIds(message.getId()).stream().findFirst().get();
        template.convertAndSendToUser(sessionId,"/topic/greetings",new ResponseMessage("single send to："+message.getId()+", from:" + message.getName() + "!"),createHeaders(sessionId));
    }
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }



    //通过simpMessagingTemplate向浏览器发送消息
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "String")
    }
    )
    @PostMapping(value = "/login1", produces = MediaType.APPLICATION_JSON_VALUE)
    public void login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,HttpSession session
            ) {
        session.setAttribute("user","1");
        System.out.println(session.getAttribute("user"));
        System.out.println(username);
        System.out.println(password);
    }

    @RequestMapping(value = "/broadcast/index")
    public String broadcastIndex(HttpServletRequest req) {
        System.out.println(req.getRemoteHost());
        return "websocket/simple/ws-broadcast";
    }

    @MessageMapping("/chat")
    public void handleChat( RequestMessage message) {
        String sessionId=webAgentSessionRegistry.getSessionIds("12345").stream().findFirst().get();
//        String default1="james";
//        String default2="curry";
//        if (default1.equals(principal.getName())) {//硬编码,对用户姓名进行判断
            //向用户发送消息,第一个参数:接收消息的用户,第二个参数:浏览器订阅地址,第三个参数:消息
        simpMessagingTemplate.convertAndSendToUser(sessionId,
                    "/queue/notifications",  "12345-send: ok ok");
//        } else {
//
//            simpMessagingTemplate.convertAndSendToUser("james",
//                    "/queue/notifications", principal.getName() + "-send: " + msg);
//        }
    }

    @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public AricResponse say(AricMessage message) {
        adasService.loop();
        try {
            //睡眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AricResponse("welcome," + message.getName() + "!");
    }

    @MessageMapping("/batches") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getBatches")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public BatchesResponse batchesStatus(BatchesMessage message) {
        String batchId = adasService.submitAdas(message);
        String state = "starting";
        while(state!="success"||state!="error") {
            try {
                //睡眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}

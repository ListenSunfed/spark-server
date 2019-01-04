package com.navinfo.sparkserver.controller;

import com.navinfo.sparkserver.config.SocketSessionRegistry;
import com.navinfo.sparkserver.model.BatchesMessage;
import com.navinfo.sparkserver.model.BatchesResponse;
import com.navinfo.sparkserver.service.AdasService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/batch")
@Api(description = "提交任务控制器")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    /**session操作类*/
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private AdasService adasService;

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

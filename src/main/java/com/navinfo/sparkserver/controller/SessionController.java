/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.controller
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 13:43
 **************************************/
package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.service.impl.SessionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*************************************
 * Class Name: sessionController
 * Description:〈会话管理〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/session")
@Api(description = "会话服务接口")
public class SessionController {

    @Resource(name = "sessionService")
    SessionServiceImpl sessionService;

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @ApiOperation(value = "根据sessionId查询某个session", notes = "通过livy代理的spark中某个session状态")
    @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/getSession", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSession(
            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId
    ) {
        Session session = sessionService.getSession(sessionId);
        logger.info("根据sessionId:" + sessionId + ",请求某个会话的信息状态：" + session);
        return JSON.toJSONString(session);
    }

    @ApiOperation(value = "创建一个session", notes = "通过livy代理的创建一个sparkShell会话")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queue", value = "申请的yarn资源队列", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverMemory", value = "spark shell运行时的driver端内存，不必分配太大", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorMemory", value = "spark shell运行时的executor端内存", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverCores", value = "spark shell运行时的driver端的cpu核数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "numExecutors", value = "spark shell运行时的executor的总个数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorCores", value = "spark shell运行时每个executor占用的cpu数", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/createSession", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createSession(
            @RequestParam(value = "queue", defaultValue = "spark") String queue,
            @RequestParam(value = "driverMemory", defaultValue = "100m") String driverMemory,
            @RequestParam(value = "executorMemory", defaultValue = "200m") String executorMemory,
            @RequestParam(value = "driverCores", defaultValue = "1") String driverCores,
            @RequestParam(value = "numExecutors", defaultValue = "1") String numExecutors,
            @RequestParam(value = "executorCores", defaultValue = "1") String executorCores
    ) {
        System.out.println(queue);
        System.out.println(driverMemory);
        System.out.println(executorMemory);
        System.out.println(numExecutors);
        System.out.println(executorCores);
//        Session session = sessionService.createSession(queue, driverMemory, executorMemory, driverCores, numExecutors, executorCores);
//        logger.info("根据sessionId:"  + session.getId() + ",请求某个会话的信息状态：" + session);
        return "{\"id\":\"1\"}";
    }


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}

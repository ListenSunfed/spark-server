/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.controller
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 13:43
 **************************************/
package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.model.StatementSimple;
import com.navinfo.sparkserver.service.impl.SessionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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


    /**
     * 根据指定sessionId查询session信息
     * @param sessionId
     * @return
     */
    @ApiOperation(value = "根据sessionId查询某个session", notes = "通过livy代理的spark中某个session状态")
    @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String")
    @GetMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSession(
            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId
    ) {
        //TODO 暂时模拟数据
//        Session session = sessionService.getSession(sessionId);
        logger.info("根据sessionId:" + sessionId + ",请求某个会话的信息状态：" + null);
        return JSON.toJSONString(null);
    }


    /**
     * 创建一个新的session
     * @param queue
     * @param driverMemory
     * @param executorMemory
     * @param driverCores
     * @param numExecutors
     * @param executorCores
     * @return
     */
    @ApiOperation(value = "创建一个session", notes = "通过livy代理的创建一个sparkShell会话")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queue", value = "申请的yarn资源队列", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverMemory", value = "spark shell运行时的driver端内存，不必分配太大", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorMemory", value = "spark shell运行时的executor端内存", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverCores", value = "spark shell运行时的driver端的cpu核数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "numExecutors", value = "spark shell运行时的executor的总个数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorCores", value = "spark shell运行时每个executor占用的cpu数", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
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
        Session session = sessionService.createSession(queue, driverMemory, executorMemory, driverCores, numExecutors, executorCores);
        logger.info("根据sessionId:"  + session.getId() + ",请求某个会话的信息状态：" + session);
//        String sessionId = "1";
        return JSON.toJSONString(session);
    }


    /**
     * 查询所有的任务
     * @param sessionId
     * @return
     */
    @ApiOperation(value = "根据sessionId查询某个session的所有任务", notes = "通过livy代理的spark中某个session状态")
    @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/getStatements", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatements(
            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId
    ) {
        //TODO 暂时模拟数据
        List<StatementSimple> statements = sessionService.getAllresults(sessionId);
        logger.info("根据sessionId:" + sessionId + ",请求某个会话的信息状态：" + null);
        return JSON.toJSONString(statements);
    }

    /**
     * 创建一个任务
     *
     * @param sessionId
     * @return
     */
    @ApiOperation(value = "在指定会话sessionId中启动某个任务", notes = "通过livy代理启动运行一个spark任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/statement", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createStateMent(
            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId,
            @RequestParam(value = "code") String code
    ) {
        //TODO 暂时模拟数据
        String submitCodeStatus = sessionService.submitCode(sessionId, code);
        logger.info("在指定会话sessionId:" + sessionId + "中启动任务:"+code+".");
        return JSON.toJSONString(submitCodeStatus);
    }


    @ApiOperation(value = "根据sessionId删除某个session务", notes = "通过sessionId删除livy代理的某个session")
    @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String")
    @DeleteMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteSession(
            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId
    ) {
        String deleteSessionState = sessionService.closeSession(sessionId);
        logger.info("根据sessionId:" + sessionId + ",请求某个会话的信息状态：" + null);
        return JSON.toJSONString(deleteSessionState);
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}

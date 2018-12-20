/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.controller
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 13:43
 **************************************/
package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.service.SessionService;
import com.navinfo.sparkserver.service.impl.SessionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/session")
@Api(description = "会话服务接口")
public class sessionController {

    @Resource(name = "sessionService")
    SessionServiceImpl sessionService;

    private static final Logger logger= LoggerFactory.getLogger(sessionController.class);

    @ApiOperation(value = "根据sessionId查询某个session", notes = "通过livy代理的spark中某个session状态")
    @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/getSession", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSession(
            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId
    ) {
        Session session = sessionService.getSession(sessionId);
        logger.info("根据sessionId:"+sessionId+",请求某个会话的信息状态："+session);
        return JSON.toJSONString(session);
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}

package com.navinfo.sparkserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/*************************************
 * Class Name: YarnResourceController
 * Description:〈yarn资源管理〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/resource")
@Api(description = "yarn资源查询接口")
public class YarnResourceController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @ApiOperation(value = "查询yarn集群剩余的cpu资源", notes = "cores")
//    @ApiImplicitParam(name = "sessionId", value = "sessionId", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/cores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCores(
//            @RequestParam(value = "sessionId", defaultValue = "0") String sessionId
    ) {
//        logger.info();
        return "{\"cores\":50}";
    }
}

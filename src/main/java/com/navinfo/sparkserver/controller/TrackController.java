/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.controller
 * Author: wulongyue06158
 * Date: Created in 2019/1/2 18:29
 **************************************/
package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.StatementSimple;
import com.navinfo.sparkserver.model.TrackSource;
import com.navinfo.sparkserver.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*************************************
 * Class Name: TrackController
 * Description:〈轨迹串控制器〉
 * @author wulongyue
 * @create 2019/1/2
 * @since 1.0.0
 ************************************/
@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/track")
@Api(description = "轨迹服务接口")
public class TrackController {

    private static final Logger logger = LoggerFactory.getLogger(TrackController.class);

    @Autowired
    private TrackService trackService;

    /**
     * 查询所有的轨迹串
     * @return
     */
    @ApiOperation(value = "查询所有的轨迹串", notes = "所有导入的记录")
    @RequestMapping(value = "/tracks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTracks(
    ) {
        List<TrackSource> tracks = trackService.getTracks();
        logger.info("查询所有轨迹串信息");
        return JSON.toJSONString(tracks);
    }

}

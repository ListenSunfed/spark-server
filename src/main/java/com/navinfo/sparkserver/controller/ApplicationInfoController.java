package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.Program;
import com.navinfo.sparkserver.model.application.Application;
import com.navinfo.sparkserver.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/applicationInfo")
@Api(description = "程序管理控制器")
public class ApplicationInfoController {

    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

    @Autowired
    private ApplicationService applicationService;

    //region 获得所有程序运行信息
    @ApiOperation(value = "获取所有的程序运行信息", notes = "获取所有的程序运行信息")
    @GetMapping(value = "/applications", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getApplications() {
        List<Application> applications = applicationService.getApplications(null, null);
//        Application application = new Application();
//        application.setApplicationName("a");
//        application.setDriverLogUrl("a");
//        application.setProgramName("a");
//        application.setSparkUiUrl("a");
//        application.setRunTime(1000);
//        application.setState("SUCCEEDED");
//        Application application1 = new Application();
//        application1.setApplicationName("b");
//        application1.setDriverLogUrl("b");
//        application1.setProgramName("b");
//        application1.setSparkUiUrl("b");
//        application1.setRunTime(2000);
//        application1.setState("KILLED");
//        List<Application> applications=applications = new ArrayList<>();
//        applications.add(application);
//        applications.add(application1);
        logger.info(String.format("获得所有程序运行信息，总共%d条",applications.size()));
        return JSON.toJSONString(applications);
    }
    //endregion
}

package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.service.AdasService;
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

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/adas1_0")
@Api(description = "adas1.0算法")
public class AdasController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private AdasService adasService;

    /**
     * 查询所有需要执行的轨迹
     * @return
     */
    @ApiOperation(value = "查询所有需要导入的轨迹", notes = "查找需要导入的轨迹串信息")
    @RequestMapping(value = "/tracks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTracks(
    ) {
        logger.info("查询所有需要导入的轨迹串");
        return null;
    }


    /**
     * 开始一次计算任务
     * @param queue
     * @param driverMemory
     * @param executorMemory
     * @param driverCores
     * @param numExecutors
     * @param executorCores
     * @return
     */
    @ApiOperation(value = "开始一次轨迹计算任务", notes = "申请资源执行一次计算任务，通过livy提交任务到yarn执行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jarPath", value = "jar包地址", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectName", value = "此次轨迹标识符", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "className", value = "任务入口", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "args", value = "任务的参数，多个任务用\"，\"隔开", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "queue", value = "申请的yarn资源队列", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverMemory", value = "spark shell运行时的driver端内存，不必分配太大", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorMemory", value = "spark shell运行时的executor端内存", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverCores", value = "spark shell运行时的driver端的cpu核数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "numExecutors", value = "spark shell运行时的executor的总个数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorCores", value = "spark shell运行时每个executor占用的cpu数", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createSession(
            @RequestParam(value = "jarPath") String jarPath,
            @RequestParam(value = "projectName") String projectName,
            @RequestParam(value = "className") String className,
            @RequestParam(value = "args") String args,
            @RequestParam(value = "queue", defaultValue = "spark") String queue,
            @RequestParam(value = "driverMemory", defaultValue = "100m") String driverMemory,
            @RequestParam(value = "executorMemory", defaultValue = "200m") String executorMemory,
            @RequestParam(value = "driverCores", defaultValue = "1") String driverCores,
            @RequestParam(value = "numExecutors", defaultValue = "1") String numExecutors,
            @RequestParam(value = "executorCores", defaultValue = "1") String executorCores
    ) {
        logger.info(String.format("启动%s",projectName));
        String adasResult = adasService.submitAdas(
                jarPath,
                projectName,
                className,
                args,
                queue,
                driverMemory,
                executorMemory,
                driverCores,
                numExecutors,
                executorCores
        );
        return JSON.toJSONString(adasResult);
    }


}

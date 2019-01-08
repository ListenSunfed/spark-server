package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.BatchesMessage;
import com.navinfo.sparkserver.model.BatchesResponse;
import com.navinfo.sparkserver.service.BatchService;
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
@RequestMapping("/batch")
@Api(description = "提交yarn任务")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private BatchService batchService;

    /**
     * 开始一次计算任务
     *
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
            @ApiImplicitParam(name = "executorCores", value = "spark shell运行时每个executor占用的cpu数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "total", value = "spark shell运行时每个executor占用的cpu数", paramType = "total", required = true, dataType = "String"),
            @ApiImplicitParam(name = "owner", value = "spark shell运行时每个executor占用的cpu数", paramType = "owner", required = true, dataType = "String"),
            @ApiImplicitParam(name = "programName", value = "spark shell运行时每个executor占用的cpu数", paramType = "programName", required = true, dataType = "String")
    })
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createSession(
            @RequestParam(value = "jarPath") String jarPath,
            @RequestParam(value = "projectName") String projectName,
            @RequestParam(value = "className") String className,
            @RequestParam(value = "args") String args,
            @RequestParam(value = "queue", defaultValue = "spark") String queue,
            @RequestParam(value = "driverMemory", defaultValue = "1g") String driverMemory,
            @RequestParam(value = "executorMemory", defaultValue = "1g") String executorMemory,
            @RequestParam(value = "driverCores", defaultValue = "1") String driverCores,
            @RequestParam(value = "numExecutors", defaultValue = "1") String numExecutors,
            @RequestParam(value = "executorCores", defaultValue = "1") String executorCores,
            @RequestParam(value = "total", defaultValue = "1") String total,
            @RequestParam(value = "owner" ) String owner,
            @RequestParam(value = "programName" ) String programName
    ) {
        logger.info(String.format("启动%s", projectName));
        String batchID = batchService.submitBatch(
                new BatchesMessage(
                jarPath,
                projectName,
                className,
                args,
                queue,
                driverMemory,
                executorMemory,
                driverCores,
                numExecutors,
                executorCores,
                        total,
                        owner,
                        programName
                )
        );
        return JSON.toJSONString(batchID);
    }

    /**
     * 根据指定batchId查询batch信息
     *
     * @param batchId
     * @return
     */
    @ApiOperation(value = "根据batchId查询某个任务的详细信息", notes = "在任务提交时会得到任务提交的信息，其中就有此次任务的id")
    @ApiImplicitParam(name = "batchId", value = "batch任务id", paramType = "query", required = true, dataType = "String")
    @GetMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBatch(
            @RequestParam(value = "batchId", defaultValue = "0") String batchId
    ) {
        BatchesResponse batchInfo = batchService.getBatchInfo(batchId);
        logger.info(String.format("根据batchId:%s查询任务的详细信息", batchId));
        return JSON.toJSONString(batchInfo);
    }

//    @MessageMapping("/batches") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
//    @SendTo("/topic/getBatches")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
//    public BatchesResponse batchesStatus() {
//        batchService.getBatchInfos();
//        return new BatchesResponse();
//    }

}

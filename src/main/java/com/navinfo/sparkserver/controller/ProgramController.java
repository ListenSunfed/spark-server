package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.Program;
import com.navinfo.sparkserver.service.ProgramService;
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

import java.util.Date;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/program")
@Api(description = "程序控制器")
public class ProgramController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private ProgramService programService;

    @ApiOperation(value = "创建一个session", notes = "通过livy代理的创建一个sparkShell会话")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "procedureName", value = "申请的yarn资源队列", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverMemory", value = "spark shell运行时的driver端内存，不必分配太大", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorMemory", value = "spark shell运行时的executor端内存", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "driverCores", value = "spark shell运行时的driver端的cpu核数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "numExecutors", value = "spark shell运行时的executor的总个数", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "executorCores", value = "spark shell运行时每个executor占用的cpu数", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/program", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProgram(
            @RequestParam(value = "procedureName") String procedureName,
            @RequestParam(value = "procedureMain") String procedureMain,
            @RequestParam(value = "procedurePath") String procedurePath,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "args") String args
    ) {
        Program program = new Program();
        program.setProcedureName(procedureName);
        program.setProcedureMain(procedureMain);
        program.setProcedurePath(procedurePath);
        program.setDescription(description);
        program.setArgs(args);
        String time = new Date().toString();
        program.setCreateTime(time);
        program.setUpdateTime(time);

        programService.registPragram(program);
        logger.info("根据sessionId:"  + ",请求某个会话的信息状态：");
//        String sessionId = "1";
        return JSON.toJSONString("");
    }

}

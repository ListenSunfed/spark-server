package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.Program;
import com.navinfo.sparkserver.model.StatementSimple;
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
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/program")
@Api(description = "程序管理控制器")
public class ProgramController {

    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

    @Autowired
    private ProgramService programService;

    //region 添加一个程序信息
    @ApiOperation(value = "创建一个新的program", notes = "注册一个新的程序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programName", value = "程序名", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "programMain", value = "程序入口", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "programPath", value = "程序地址", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述程序的具体信息", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "args", value = "程序需要的参数名", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/program", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProgram(
            @RequestParam(value = "programName") String programName,
            @RequestParam(value = "programMain") String programMain,
            @RequestParam(value = "programPath") String programPath,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "args") String args
    ) {
        String time = DateUtils.getNowDateStr();
        Program program = new Program(programName, programMain, programPath, description, args, time, time);
        Program program1 = programService.getProgram(programName);
        if(program1!=null){
            programService.updateProgram(program);
            logger.info(String.format("程序已经存在，进行更新：%s", program));
        }
        logger.info(String.format("注册一个新的程序：%s", program));
        int result = programService.registPragram(program);
        logger.info(String.format("程序注册结果：%d", result));
        return JSON.toJSONString(program);
    }
    //endregion

    //region 更新程序信息
    @ApiOperation(value = "更新program", notes = "更新程序的相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programName", value = "程序名", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "programMain", value = "程序入口", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "programPath", value = "程序地址", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述程序的具体信息", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "args", value = "程序需要的参数名", paramType = "query", required = true, dataType = "String")
    })
    @PutMapping(value = "/program", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateProgram(
            @RequestParam(value = "programName") String programName,
            @RequestParam(value = "programMain") String programMain,
            @RequestParam(value = "programPath") String programPath,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "args") String args
    ) {
        String time = DateUtils.getNowDateStr();
        Program program = new Program(programName, programMain, programPath, description, args, time, time);
        logger.info(String.format("更新一个新的程序:%s", program));
        int result = programService.updateProgram(program);
        logger.info(String.format("程序更新结果:%d", result));
        return JSON.toJSONString(program);
    }
    //endregion

    //region 删除程序信息
    @ApiOperation(value = "根据程序名删除程序信息", notes = "根据程序名删除对应的程序信息")
    @ApiImplicitParam(name = "programName", value = "程序名", paramType = "query", required = true, dataType = "String")
    @DeleteMapping(value = "/program", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delProgram(@RequestParam(value = "programName") String programName) {
        programService.delProgram(programName);
        logger.info(String.format("删除程序：%s", programName));
        return "ok";
    }
    //endregion

    //region 根据程序名查找相应具体信息
    @ApiOperation(value = "根据程序名查找相应具体信息", notes = "根据程序名查找相应具体信息")
    @ApiImplicitParam(name = "programName", value = "程序名", paramType = "query", required = true, dataType = "String")
    @GetMapping(value = "/program", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProgram(@RequestParam(value = "programName") String programName) {
        Program program = programService.getProgram(programName);
        logger.info(String.format("根据%s获得程序具体信息：%s", programName, program));
        return JSON.toJSONString(program);
    }
    //endregion

    //region 获得所有程序信息
    @ApiOperation(value = "获取所有的程序信息", notes = "获得所有的程序信息")
    @GetMapping(value = "/programs", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPrograms() {
        List<Program> programs = programService.getPrograms();
//        List<Program> programs = new ArrayList<>();
//        Program program = new Program("test1", "testMain", "a", "a", "a", "a", "a");
//        Program program1 = new Program("test2", "testMain2", "a", "a", "a", "a", "a");
//        programs.add(program);
//        programs.add(program1);
        logger.info(String.format("获得所有程序信息，总共%d条", programs.size()));
        return JSON.toJSONString(programs);
    }
    //endregion

}

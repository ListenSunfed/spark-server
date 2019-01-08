package com.navinfo.sparkserver.controller;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.model.application.Application;
import com.navinfo.sparkserver.model.hbase.ImportRecord;
import com.navinfo.sparkserver.service.ImportRecordService;
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

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/hbase")
@Api(description = "导入数据查找")
public class ImportRecordController {

    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

    @Autowired
    private ImportRecordService importRecordService;

    //region 获得所有程序运行信息导入数据信息", notes = "获得所有程序运行信息导入数据信息")
    @GetMapping(value = "/importrecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getApplications() {
        List<ImportRecord> records = importRecordService.getRecords();
        logger.info(String.format("获得所有程导入记录信息，总共%d条",records.size()));
        return JSON.toJSONString(records);
    }
}

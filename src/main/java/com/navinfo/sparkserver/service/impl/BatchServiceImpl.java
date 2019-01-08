package com.navinfo.sparkserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.navinfo.sparkserver.dao.ApplicationDao;
import com.navinfo.sparkserver.livy.LivyTool;
import com.navinfo.sparkserver.model.AricResponse;
import com.navinfo.sparkserver.model.BatchesMessage;
import com.navinfo.sparkserver.model.BatchesResponse;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.model.application.ApplicationInfo;
import com.navinfo.sparkserver.service.BatchService;
import com.navinfo.sparkserver.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service("adasService")
public class BatchServiceImpl implements BatchService {

    @Value("${livy.url}")
    String livyUrl;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public String submitBatch(BatchesMessage message) {
        String url = String.format("%s/batches",livyUrl);
        String postData = "{\"kind\": \"spark\"}";
        String pData = String.format(
                "{\"file\":\"%s\"" +
                ",\"className\":\"%s\"" +
                ",\"name\":\"%s\"" +
                ",\"numExecutors\":%s " +
                ",\"executorCores\":%s " +
                ",\"executorMemory\":\"%s\"" +
                ",\"driverCores\":%s" +
                ",\"driverMemory\":\"%s\"" +
                ",\"args\":[%s]}",
                message.getJarPath(),
                message.getClassName(),
                message.getProjectName(),
                message.getNumExecutors(),
                message.getExecutorCores(),
                message.getExecutorMemory(),
                message.getDriverCores(),
                message.getDriverMemory(),
                message.getArgs()
                );
        String reqResult = LivyTool.sendPostReq(url, pData);
        String batchId = JSON.parseObject(reqResult).getString("id");
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime=System.currentTimeMillis();
                BatchesResponse response = getBatchInfo(batchId);
//                String batchInfo = getBatchInfo(batchId);
                while ("starting".equals(response.getState())||"running".equals(response.getState())) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    response = getBatchInfo(batchId);
                    if(response.getDriverLogUrl()==null){
                        response.setDriverLogUrl(String.format("%s/ui/batch/%s/log",livyUrl,batchId));
                    }
                    template.convertAndSend("/topic/getBatches", response);
                }
                long endTIme = System.currentTimeMillis();
                ApplicationInfo applicationInfo = null;
                if (response.getAppId()==null) {
                    applicationInfo = new ApplicationInfo(
                            String.format("submitFaild%s", new Date().toString()),
                            message.getArgs(),
                            "queue",
                            message.getDriverMemory(),
                            message.getExecutorMemory(),
                            message.getDriverCores(),
                            message.getNumExecutors(),
                            message.getExecutorCores(),
                            message.getTotal(),
                            message.getOwner(),
                            startTime,
                            endTIme
                    );
                    applicationInfo.setProgramName(message.getProgramName());
                    applicationInfo.setApplicationName(message.getProjectName());
                }else {
                    applicationInfo = new ApplicationInfo(
                            response.getAppId(),
                            message.getArgs(),
                            "queue",
                            message.getDriverMemory(),
                            message.getExecutorMemory(),
                            message.getDriverCores(),
                            message.getNumExecutors(),
                            message.getExecutorCores(),
                            message.getTotal(),
                            message.getOwner(),
                            startTime,
                            endTIme
                    );
                    applicationInfo.setApplicationName(message.getProjectName());
                    applicationInfo.setDriverLogUrl(response.getDriverLogUrl());
                    applicationInfo.setSparkUiUrl(response.getSparkUiUrl());
                    applicationInfo.setState(response.getState());
                }
                applicationDao.addApplicationInfo(applicationInfo);

            }
        }).start();
        return batchId;
    }

    @Override
    public BatchesResponse getBatchInfo(String batchId) {
        String url = String.format("%s/batches/%s",livyUrl,batchId);
        String result = "";
        BatchesResponse batchInfo = new BatchesResponse();
        try {
            result = LivyTool.requestGetJson(url, null);
            JSONObject jsonObject = JSON.parseObject(result);
            batchInfo.setBatchId(jsonObject.getString("id"));
            batchInfo.setAppId(jsonObject.getString("appId"));
            batchInfo.setState(jsonObject.getString("state"));
            batchInfo.setDriverLogUrl(jsonObject.getString("driverLogUrl"));
            batchInfo.setSparkUiUrl(jsonObject.getString("sparkUiUrl"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return batchInfo;
    }

    @Override
    public List<BatchesResponse> getBatchInfos() {
        List<Session> allSession = sessionService.getAllSession();
        return null;
    }
}

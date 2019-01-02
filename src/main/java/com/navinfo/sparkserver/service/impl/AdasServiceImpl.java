package com.navinfo.sparkserver.service.impl;

import com.navinfo.sparkserver.livy.LivyTool;
import com.navinfo.sparkserver.service.AdasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service("adasService")
public class AdasServiceImpl implements AdasService {

    @Value("${livy.ur}")
    String livyUrl;

    @Override
    public String submitAdas(String jarPath,String projectName,String className, String args, String queue, String driverMemory, String executorMemory, String driverCores, String numExecutors, String executorCores) {
        String url = String.format("%s/batches",livyUrl);
        String postData = "{\"kind\": \"spark\"}";
        String pData = String.format(
                "{\"file\":\"%s\"" +
                ",\"className\":\"%s\"" +
                ",\"name\":\"%s\"" +
                ",\"numExecutors\":%s }" +
                ",\"executorCores\":%s }" +
                ",\"executorMemory\":\"%s\"" +
                ",\"driverCores\":%s" +
                ",\"driverMemory\":\"%s\"" +
                ",\"args\":[\"%s\"]",
                jarPath,
                className,
                projectName,
                numExecutors,
                executorCores,
                executorMemory,
                driverCores,
                driverMemory,
                args
                );
        String reqResult = LivyTool.sendPostReq(url, pData);
        return reqResult;
    }
}

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.service.impl
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 14:23
 **************************************/
package com.navinfo.sparkserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.navinfo.sparkserver.livy.LivyTool;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/*************************************
 * Class Name: SessionServiceImpl
 * Description:〈session服务的实现〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
@Slf4j
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    @Value("${livy.url}")
    String livyUrl;

    @Override
    public Session createSession(String queue, String driverMemory, String executorMemory, String driverCores, String numExecutors, String executorCores) {
        String url = livyUrl + "/sessions";
        String postData = "{" +
                "\"kind\":\"spark\"," +
                "\"queue\":quqe," +
                "\"driverMemory\":driverMemory," +
                "\"executorMemory\":executorMemory," +
                "\"driverCores\":driverCores," +
                "\"numExecutors\":numExecutors," +
                "\"executorCores\":executorCores" +
                "}";
        String result = LivyTool.sendPostReq(url, postData);
        return JSON.parseObject(result, Session.class);
    }

    @Override
    public Session getSession(String sessionID) {
        String url = livyUrl + "/sessions/" + sessionID;
        String result = "";
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = JSON.parseObject(result, Session.class);
        return session;
    }

    @Override
    public List<Session> getAllSession() {
        String result = "";
        String url = livyUrl + "/sessions";
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Session> sessions = JSON.parseArray(JSON.parseObject(result).getString("sessions"), Session.class);
        return sessions;
    }

    @Override
    public void closeSession(String sessionID) {
        String result = "";
        String url = livyUrl + "/sessions/" + sessionID;
        try {
            result = LivyTool.requestDeleteJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String submitSql(String sessionId, String sql) {
        String result = "";
        String url = livyUrl + "/sessions/" + sessionId + "/statements";
        String postData = "{\"code\":\"" + sql + "\",\"kind\":\"sql\"}";
        result = LivyTool.sendPostReq(url, postData);
        return result;
    }

    @Override
    public String getAllresults(String sessionId) {
        return null;
    }

    @Override
    public String getlResult(String sessionId, String statement) {
        return null;
    }
}

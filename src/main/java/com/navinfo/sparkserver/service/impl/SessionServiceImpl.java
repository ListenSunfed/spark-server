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
import com.navinfo.sparkserver.model.Statement;
import com.navinfo.sparkserver.model.StatementSimple;
import com.navinfo.sparkserver.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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

    @Value("${livy.ur}")
    String livyUrl;

    @Override
    public Session createSession(String queue, String driverMemory, String executorMemory, String driverCores, String numExecutors, String executorCores) {
        String url = livyUrl + "/sessions";
        String postData = "{" +
                "\"kind\":\"spark\"," +
//                "\"queue\":\""+queue+"\"," +
                "\"driverMemory\":\""+driverMemory+"\"," +
                "\"executorMemory\":\""+executorMemory+"\"," +
                "\"driverCores\":"+driverCores+"," +
                "\"numExecutors\":"+numExecutors+"," +
                "\"executorCores\":"+executorCores+
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
    public String closeSession(String sessionID) {
        String result = "";
        String url = livyUrl + "/sessions/" + sessionID;
        try {
            result = LivyTool.requestDeleteJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
    public String submitCode(String sessionId, String code) {
        String result = "";
        String url = livyUrl + "/sessions/" + sessionId + "/statements";
        String postData = "{\"code\":\"" + code+"\"}";
        result = LivyTool.sendPostReq(url, postData);
        log.info(code+"的执行结果是："+result);
        return result;
    }

    @Override
    public List<StatementSimple> getAllresults(String sessionId) {
        String result = "";
        String url = livyUrl + "/sessions/" + sessionId + "/statements";
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        List<StatementSimple> statements = new ArrayList<>();
        statements.add(new StatementSimple("1","select * from aaa","ok"));
        statements.add(new StatementSimple("2","select * from bbb","ok"));
        statements.add(new StatementSimple("3","select * from ccc","ok"));
        statements.add(new StatementSimple("4","select * from ddd","running"));
        return statements;
    }

    @Override
    public String getlResult(String sessionId, String statement) {
        return null;
    }
}

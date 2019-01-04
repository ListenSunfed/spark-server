/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.backupservice.utils.com.navinfo.sparkmanange.livy
 * Author: wulongyue06158
 * Date: Created in 2018/12/17 16:17
 **************************************/
package com.navinfo.sparkserver.livy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.model.Statement;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/*************************************
 * Class Name: SubmitDemo
 * Description:〈测试livy提交〉
 * @author wulongyue
 * @create 2018/12/17
 * @since 1.0.0
 ************************************/
public class  SubmitDemo {


    /**
     * 创建一个新的交互式会话
     *
     * @return
     */
    public static String createSession() {
        String url = "http://datalake-data04:8998/sessions";
        String postData = "{\"kind\":\"spark\"}";
        String result = LivyTool.sendPostReq(url, postData);
        JSONObject jsonObject = JSON.parseObject(result);
        Session session = JSON.parseObject(result, Session.class);
        return session.toString();
    }

    public static String getSessions(String sessionID) {
        String result = "";
        String url = "http://datalake-data04:8998/sessions/"+sessionID;
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = JSON.parseObject(result, Session.class);
        System.out.println(session);
        return result;
    }

    /**
     * 查看所有交互会话列表
     *
     * @return
     */
    public static String listSessions() {
        String result = "";
        String url = "http://datalake-data04:8998/sessions";
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Session> sessions = JSON.parseArray(JSON.parseObject(result).getString("sessions"), Session.class);
        for (Session session: sessions){
            System.out.println(session);
        }
        return result;
    }


    /**
     * 提交sql代码片段
     * @param sessionId 会话id
     * @param sql   sql语句
     * @return
     */
    public static String submitSql(String sessionId, String sql) {
        String result = "";
        String url = "http://datalake-data04:8998/sessions/" + sessionId + "/statements";
        String postData = "{\"code\":\"" + sql + "\",\"kind\":\"sql\"}";
        result = LivyTool.sendPostReq(url, postData);
        return result;
    }

    /**
     * 查询所有任务列表
     * @param sessionId
     * @return
     */
    public static String listStatments(String sessionId) {
        String result = "";
        String url = "http://datalake-data04:8998/sessions/" + sessionId + "/statements";
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行sql
     * @param sessionId
     * @param sqlId
     * @return
     */
    public static String executeSql(String sessionId,String sqlId){
        String result = "";
        String url = "http://datalake-data04:8998/sessions/" + sessionId + "/statements/"+sqlId;
        try {
            result = LivyTool.requestGetJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Statement statement = JSON.parseObject(result.replace("application/json","applicationJson"), Statement.class);
        return statement.toString();
    }

    /**
     * 删除一个会话
     * @param sessionId
     * @return
     */
    public static String deleteSession(String sessionId){
        String result = "";
        String url = "http://datalake-data04:8998/sessions/" + sessionId;
        try {
            result = LivyTool.requestDeleteJson(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过Livy 提交spark 任务到yarn 集群
     */
    public static void invoke() {
//        log.info("~~ invoke task");
        String url = "http://datalake-data04:8998/batches";
        String postData = "{\"kind\": \"spark\"}";
        String pData = "{\"file\":\"/backup/jars/spark-examples_2.11-2.2.0.jar\"" +
                ",\"className\":\"org.apache.spark.examples.SparkPi\"" +
                ",\"name\":\"com.navinfo.sparkmanange.livy submit sparkPi\"" +
                ",\"executorCores\":5 }" +
                ",\"executorMemory\":\"512m\"" +
                ",\"driverCores\":1" +
                ",\"driverMemory\":\"512m\"" +
                ",\"args\":[\"100\"]";
        String reqResult = LivyTool.sendPostReq(url, pData);
        System.out.println(reqResult);
    }
    public static void main(String[] args) throws IOException {

//        String url = "http://datalake-data04:8998/batches";
//
//        String postData = "{\"kind\": \"spark\"}";
//        String pData = "{\"file\":\"hdfs://datalake-cluster/jar/spark-examples-1.6.3-hadoop2.6.0.jar\",\"className\":\"org.apache.spark.examples.SparkPi\"}";
////        String reqResult = LivyTool.sendPostReq(url, pData);
//        String reqResult = LivyTool.sendPostReq(url,postData);
//        System.out.println(reqResult);

        //1 创建新的会话
        String result = createSession();
//        System.out.println(result);
//
        //2 查看所有会话
//        String result = listSessions();
//        System.out.println(result);

//        3 提交sql代码片段
//        String sql="select * from test_mass_track_am limit 10";
//        String result = submitSql("17", sql);
//        System.out.println(result);

        //4 执行sql
//        String result = executeSql("17", "0");
//        System.out.println(result);

        //5 删除指定会话
//        String result = deleteSession("16");
//        System.out.println(result);

        //6 查看所有任务详情
//        String result = listStatments("12");
//        System.out.println(result);

        //
//        getSessions("17");

//        invoke();

//        try {
//            livyClientTest();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
    }


    private static Logger log = Logger.getLogger(SubmitDemo.class);

}

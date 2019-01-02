/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.service
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 14:16
 **************************************/
package com.navinfo.sparkserver.service;


import com.navinfo.sparkserver.model.Session;
import com.navinfo.sparkserver.model.StatementSimple;

import java.util.List;

/*************************************
 * Class Name: SessionService
 * Description:〈sesion服务〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
public interface SessionService {

    /**
     * 创建新的会话
     * @return
     * @param queue
     * @param driverMemory
     * @param executorMemory
     * @param driverCores
     * @param numExecutors
     * @param executorCores
     */
    Session createSession(String queue, String driverMemory, String executorMemory, String driverCores, String numExecutors, String executorCores);

    /**
     * 根据session查找指定session
     * @param sessionID
     * @return
     */
    Session getSession(String sessionID);

    /**
     * 获得所有的会话窗口id
     * @return
     */
    List<Session> getAllSession();

    /**
     * 关闭指定会话窗口
     * @return
     */
    String closeSession(String sessionID);

    /**
     * 提交sql请求
     * @param sessionId
     * @param sql
     * @return
     */
    String submitSql(String sessionId, String sql);

    /**
     * 提交代码片段请求
     * @param sessionId
     * @param code
     * @return
     */
    String submitCode(String sessionId, String code);

    /**
     * 查询指定会话的所有任务结果
     * @param sessionId
     * @return
     */
    List<StatementSimple> getAllresults(String sessionId);

    /**
     * 查询指定会话的指定任务结果
     * @param sessionId
     * @param statement
     * @return
     */
    String getlResult(String sessionId, String statement);

}

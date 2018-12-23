/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.model
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 14:53
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/*************************************
 * Class Name: Session
 * Description:〈livySession〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
@Getter
@ToString
public class Session {
//    /**
//    *  Session Kind
//     * Value	Description
//     * spark	Interactive Scala Spark session
//     * pyspark	Interactive Python Spark session
//     * sparkr	Interactive R Spark session
//     * sql	    Interactive SQL Spark session
//    */
//    private String kind;
//
//    /**
//    *proxyUser	User to impersonate when starting the session
//    */
//    private String proxyUser;
//
//    /**
//    *driverMemory	Amount of memory to use for the driver process
//    */
//    private String driverMemory;
//
//    /**
//    *driverCores	Number of cores to use for the driver process
//    */
//    private int driverCores;
//
//    /**
//    *executorMemory	Amount of memory to use per executor process
//    */
//    private String executorMemory;
//
//    /**
//    *executorCores	Number of cores to use for each executor
//    */
//    private int executorCores;
//
//    /**
//    *numExecutors	Number of executors to launch for this session
//    */
//    private int numExecutors;
//
////    /**
////    *archives	Archives to be used in this session
////    */
////    private String archives;
//
//    /**
//     *queue	The name of the YARN queue to which submitted
//     */
//    private String queue;
//
//    /**
//     *name	The name of this session
//     */
//    private String name;
//
//    /**
//     *heartbeatTimeoutInSecond	Timeout in second to which session be orphaned
//     */
//    private int heartbeatTimeoutInSecond;

    /**
     * sessionID
     */
    private String id;

    /**
     * applicationID yarn中的id
     */
    private String appId;

    /**
     * 所属用户
     */
    private String owner;

    /**
     * 会话当前状态
     */
    private String state;

    /**
     * 地址对象
     */
    private AppInfo appInfo;

}

@Data
@ToString
class AppInfo {

    /**
     * yarn日志地址
     */
    private String driverLogUrl;

    /**
     * 运行任务时spark的webui
     */
    private String sparkUiUrl;
}

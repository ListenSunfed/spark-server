/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model.application
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 15:23
 **************************************/
package com.navinfo.sparkserver.model.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*************************************
 * Class Name: ApplicationInfo
 * Description:〈application详细信息〉
 * @author wulongyue
 * @create 2019/1/7
 * @since 1.0.0
 ************************************/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInfo extends Application {

    /**
     * 每次运行任务会产生一个新的
     */
    private String applicationId;


    /**
     * 运行任务传递的参数
     */
    private String args;

    /**
     * yarn资源队列
     */
    private String queue;

    /**
     * 运行任务时申请的driver端内存
     */
    private String driverMemory;

    /**
     * 运行任务时申请的executor端内存
     */
    private String executorMemory;

    /**
     * 运行任务时申请的driver的核数
     */
    private String driverCores;

    /**
     * 运行任务时申请的executor个数
     */
    private String numExecutors;

    /**
     * 运行任务时申请的executor核数
     */
    private String executorCores;

    /**
     * 处理记录总数
     */
    private String recordCount;

    /**
     * 任务负责人
     */
    private String owner;

    /**
     * 任务开始时间
     */
    private long startTime;

    /**
     * 任务结束时间
     */
    private long endTime;

}

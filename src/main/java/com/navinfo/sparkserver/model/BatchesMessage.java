/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model
 * Author: wulongyue06158
 * Date: Created in 2019/1/3 10:53
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*************************************
 * Class Name: BatchesMessage
 * Description:〈提交batch任务〉
 * @author wulongyue
 * @create 2019/1/3
 * @since 1.0.0
 ************************************/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BatchesMessage {
    /**
     *
     */
    private String jarPath;

    /**
     *
     */
    private String projectName;

    /**
     *
     */
    private String className;

    /**
     *
     */
    private String args;

    /**
     *
     */
    private String queue;

    /**
     *
     */
    private String driverMemory;

    /**
     *
     */
    private String executorMemory;

    /**
     *
     */
    private String driverCores;

    /**
     *
     */
    private String numExecutors;

    /**
     *
     */
    private String executorCores;

    /**
     *
     */
    private String total;

    /**
     *
     */
    private String owner;

    /**
     * 运行任务的程序
     */
    private String programName;

}

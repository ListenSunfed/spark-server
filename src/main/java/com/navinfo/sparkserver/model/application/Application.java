/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model.application
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 15:19
 **************************************/
package com.navinfo.sparkserver.model.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*************************************
 * Class Name: Application
 * Description:〈运行程序的概要信息〉
 * @author wulongyue
 * @create 2019/1/7
 * @since 1.0.0
 ************************************/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    /**
     * 运行任务的程序
     */
    private String programName;

    /**
     * 表示任务的唯一标识
     */
    private String applicationName;

    /**
     * 任务运行的日志地址
     */
    private String driverLogUrl;

    /**
     * 任务运行时详细的状态信息
     */
    private String sparkUiUrl;

    /**
     * 任务的状态
     */
    private String state;

    /**
     * 任务运行时间
     */
    private long runTime;

}

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.model
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 14:53
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************
 * Class Name: Session
 * Description:〈livySession〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
@Getter
@Setter
@ToString
public class Session {

    /**
     *sessionID
     */
    private String id;

    /**
     *applicationID yarn中的id
     */
    private String appId;

    /**
     *所属用户
     */
    private String owner;

    /**
     *代理用户
     */
    private String proxyUser;

    /**
     *会话当前状态
     */
    private String state;

    /**
     *种类
     */
    private String kind;

    /**
     * 地址对象
     */
    private AppInfo appInfo;

}

@Getter
@Setter
@ToString
class AppInfo {

    /**
     *yarn日志地址
     */
    private String driverLogUrl;

    /**
     * 运行任务时spark的webui
     */
    private String sparkUiUrl;
}

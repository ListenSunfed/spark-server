/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.service
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 18:52
 **************************************/
package com.navinfo.sparkserver.service;

import com.navinfo.sparkserver.model.application.Application;
import com.navinfo.sparkserver.model.application.ApplicationInfo;

import java.util.List;

/*************************************
 * Class Name: ApplicationService
 * Description:〈任务信息服务〉
 * @author wulongyue
 * @create 2019/1/7
 * @since 1.0.0
 ************************************/
public interface ApplicationService {

    int addApplicationInfo(ApplicationInfo applicationInfo);

    ApplicationInfo getApplicationInfo(String applicationId);

    List<Application> getApplications(String programName,String state);

}

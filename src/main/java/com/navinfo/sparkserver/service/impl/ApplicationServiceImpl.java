/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.service.impl
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 18:59
 **************************************/
package com.navinfo.sparkserver.service.impl;

import com.navinfo.sparkserver.dao.ApplicationDao;
import com.navinfo.sparkserver.model.application.Application;
import com.navinfo.sparkserver.model.application.ApplicationInfo;
import com.navinfo.sparkserver.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*************************************
 * Class Name: ApplicationServiceImpl
 * Description:〈实现任务状态信息服务〉
 * @author wulongyue
 * @create 2019/1/7
 * @since 1.0.0
 ************************************/
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public int addApplicationInfo(ApplicationInfo applicationInfo) {
        int i = applicationDao.addApplicationInfo(applicationInfo);
        return i;
    }

    @Override
    public ApplicationInfo getApplicationInfo(String applicationId) {
        ApplicationInfo applicationInfo = applicationDao.getApplicationInfo(applicationId);
        return applicationInfo;
    }

    @Override
    public List<Application> getApplications(String programName, String state) {
        StringBuffer sb = new StringBuffer();
        if(programName!=null){
            if(state != null){
                sb.append(String.format("where program_name = '%s' and state = '%s'",programName,state));
            }else{
                sb.append(String.format("where program_name = '%s'",programName));
            }
        }else if(state != null){
            sb.append(String.format("where state = '%s'",state));
        }
        applicationDao.getApplications(sb.toString());
        return null;
    }
}

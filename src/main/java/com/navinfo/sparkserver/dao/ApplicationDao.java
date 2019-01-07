/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.dao
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 15:41
 **************************************/
package com.navinfo.sparkserver.dao;

import com.navinfo.sparkserver.model.Program;
import com.navinfo.sparkserver.model.application.Application;
import com.navinfo.sparkserver.model.application.ApplicationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*************************************
 * Class Name: ApplicationDao
 * Description:〈存储任务运行的信息〉
 * @author wulongyue
 * @create 2019/1/7
 * @since 1.0.0
 ************************************/
@Repository(value = "applicationDao")
public class ApplicationDao extends CommonWrapDao<ApplicationInfo> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationDao.class);

    @Value("${spring.datasource.table.t_application_info}")
    String t_application_info;

    //region pg库中添加一条任务运行信息
    /**
     * pg库中添加一条任务运行信息
     *
     * @param applicationInfo
     * @return
     */
    public int addApplicationInfo(ApplicationInfo applicationInfo) {
        int result = -1;
        String sql = String.format("insert into %s " +
                        "(" +
                        "program_name," +
                        "args," +
                        "application_name," +
                        "application_id," +
                        "driver_log_url," +
                        "spark_ui_url," +
                        "state," +
                        "queue," +
                        "driver_memory," +
                        "executor_memory," +
                        "driver_cores," +
                        "num_executors," +
                        "executor_cores," +
                        "record_count," +
                        "owner," +
                        "start_time," +
                        "end_time" +
                        ") " +
                        "VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                t_application_info,
                applicationInfo.getProgramName(),
                applicationInfo.getArgs(),
                applicationInfo.getApplicationName(),
                applicationInfo.getApplicationId(),
                applicationInfo.getDriverLogUrl(),
                applicationInfo.getSparkUiUrl(),
                applicationInfo.getState(),
                applicationInfo.getQueue(),
                applicationInfo.getDriverMemory(),
                applicationInfo.getExecutorMemory(),
                applicationInfo.getDriverCores(),
                applicationInfo.getNumExecutors(),
                applicationInfo.getExecutorCores(),
                applicationInfo.getRecordCount(),
                applicationInfo.getOwner(),
                applicationInfo.getStartTime(),
                applicationInfo.getEndTime()
        );
        logger.info(String.format("添加一条运行任务的信息，sql：%s", sql));
        result = jdbcTemplate.update(sql);
        return result;
    }
    //endregion


    //region 不需要单独获取
    //    /**
//     * 获得所有成功程序信息
//     *
//     * @return
//     */
//    public List<Application> getSucceededApplication() {
//        String sql = String.format("select " +
//                        "program_name as \"programName\"," +
//                        "application_name as \"applicationName\"," +
//                        "driver_log_url as \"driverLogUrl\"," +
//                        "spark_ui_url as \"sparkUiUrl\"," +
//                        "state as \"state\"," +
//                        "(end_time - start_time) as \"runTime\" " +
//                        "from %s " +
//                        "where state = '%s';",
//                t_application_info,
//                "SUCCEEDED"
//        );
//        List<Application> applications = getResults(sql);
//        return applications;
//    }
//
//    /**
//     * 获得所有杀死的程序信息
//     *
//     * @return
//     */
//    public List<Application> getKilledApplication() {
//        String sql = String.format("select " +
//                        "program_name as \"programName\"," +
//                        "application_name as \"applicationName\"," +
//                        "driver_log_url as \"driverLogUrl\"," +
//                        "spark_ui_url as \"sparkUiUrl\"," +
//                        "state as \"state\"," +
//                        "(end_time - start_time) as \"runTime\" " +
//                        "from %s " +
//                        "where state = '%s';",
//                t_application_info,
//                "KILLED"
//        );
//        List<Application> applications = getResults(sql);
//        return applications;
//    }
//
//    /**
//     * 获得所有UNDEFINED的程序信息
//     *
//     * @return
//     */
//    public List<Application> getUndefinedApplication() {
//        String sql = String.format("select " +
//                        "program_name as \"programName\"," +
//                        "application_name as \"applicationName\"," +
//                        "driver_log_url as \"driverLogUrl\"," +
//                        "spark_ui_url as \"sparkUiUrl\"," +
//                        "state as \"state\"," +
//                        "(end_time - start_time) as \"runTime\" " +
//                        "from %s " +
//                        "where state = '%s';",
//                t_application_info,
//                "UNDEFINED"
//        );
//        List<Application> applications = getResults(sql);
//        return applications;
//    }
//
//    /**
//     * 获得所有FAILED的程序信息
//     *
//     * @return
//     */
//    public List<Application> getFailedApplication() {
//        String sql = String.format("select " +
//                        "program_name as \"programName\"," +
//                        "application_name as \"applicationName\"," +
//                        "driver_log_url as \"driverLogUrl\"," +
//                        "spark_ui_url as \"sparkUiUrl\"," +
//                        "state as \"state\"," +
//                        "(end_time - start_time) as \"runTime\" " +
//                        "from %s " +
//                        "where state = '%s';",
//                t_application_info,
//                "FAILED"
//        );
//        List<Application> applications = getResults(sql);
//        return applications;
//    }
    //endregion


    //region 根据过滤条件查询符合条件的所有任务信息
    /**
     * 获得所有程序信息
     *
     * @return
     */
    public List<Application> getApplications(String str) {
        String sql = String.format("select " +
                "program_name as \"programName\"," +
                "application_name as \"applicationName\"," +
                "driver_log_url as \"driverLogUrl\"," +
                "spark_ui_url as \"sparkUiUrl\"," +
                "state as \"state\"," +
                "(end_time - start_time) as \"runTime\" " +
                "from %s %s;",
                t_application_info,
                str
                );
        List<Application> applications = getResults(sql);
        return applications;
    }


    public List<Application> getResults(String sql) {
        logger.info(String.format("查找程序信息，sql：%s", sql));
        List<ApplicationInfo> applicationInfos = listAll(sql);
        List<Application> applications = new ArrayList<>();
        for (ApplicationInfo applicationInfo : applicationInfos) {
            Application application = applicationInfo;
            applications.add(application);
        }
        return applications;
    }
    //endregion


    //region 根据applicationId插叙详细信息
    /**
     * 根据applicationId插叙详细信息
     * @param applicationId
     * @return
     */
    public ApplicationInfo getApplicationInfo(String applicationId) {
        ApplicationInfo applicationInfo = null;
        String sql = String.format(
                "select " +
                        "program_name as \"programName\"," +
                        "args," +
                        "application_name as \"applicationName\"," +
                        "application_id as \"applicationId\"," +
                        "driver_log_url as \"driverLogUrl\"," +
                        "driver_log_url as \"driverLogUrl\"," +
                        "spark_ui_url as \"sparkUiUrl\"," +
                        "queue as \"queue\"," +
                        "driver_memory as \"driverMemory\"," +
                        "executor_memory as \"executorMemory\"," +
                        "driver_cores as \"driverCores\"," +
                        "num_executors as \"numExecutors\"," +
                        "executor_cores as \"executorCores\"," +
                        "record_count as \"recordCount\"," +
                        "owner," +
                        "start_time as \"startTime\"," +
                        "end_time as \"endTime\"," +
                        "from %s " +
                        "where application_id = '%s';",
                t_application_info,
                applicationId
        );
        logger.info(String.format("根据%s applicationId查询任务详情，sql：%s", applicationId, sql));
        applicationInfo = getBean(sql);
        logger.info(String.format("applicationId：%s程查询结果：%s", applicationInfo.getApplicationId(), applicationInfo));
        return applicationInfo;
    }
    //endregion
}

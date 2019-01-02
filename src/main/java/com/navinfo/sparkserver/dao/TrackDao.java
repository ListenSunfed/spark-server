/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.dao
 * Author: wulongyue06158
 * Date: Created in 2019/1/2 16:18
 **************************************/
package com.navinfo.sparkserver.dao;

import com.navinfo.sparkserver.model.TrackSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/*************************************
 * Class Name: TrackDao
 * Description:〈查询所有需要导入的轨迹信息〉
 * @author wulongyue
 * @create 2019/1/2
 * @since 1.0.0
 ************************************/
@Repository(value="trackDao")
public class TrackDao extends CommonWrapDao<TrackSource>{

    private static final Logger logger = LoggerFactory.getLogger(TrackDao.class);

    @Value("${spring.datasource.table.t_data_import_log}")
    private String t_data_import_log;

    /**
     * 列出所有需要备份的表
     * @return
     */
    public List<TrackSource> listAllTrack(){
        String sql = "select project_id as \"projectId\",status,err_type as \"errType\",algorithm_code as \"algorithmCode\",create_time as \"createTime\",update_time as \"updateTime\" from "+ t_data_import_log;
        logger.info("执行sql语句："+sql);
        List<TrackSource> trackSources = listAll(sql);
        return trackSources;
    }

}

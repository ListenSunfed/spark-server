package com.navinfo.sparkserver.dao; /**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.datalaker.ADASMapService.dao
 * Author: liumingkai05559
 * Date: Created in 2018/4/8 10:47
 **************************************/


import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/*************************************
 * Class Name: CommonDao
 * Description:〈通用数据Dao〉
 * @author liumingkai
 * @create 2018/4/8
 * @since 1.0.0
 ************************************/
public class CommonDao {
    @Resource(name="jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    /**
     * 获取表记录总数
     * @param tableName 表名
     * @return 记录总数
     */
    public int getTotal(String tableName){
        String sql="select count(*) as total from "+tableName;
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    /**
     * 根据条件获取表记录总数
     * @param tableName 表名
     * @param whereSql 关键字
     * @return 记录总数
     */
    public int getTotal(String tableName, String whereSql){
        String sql="select count(*) as total from "+tableName;
        if(StringUtils.isNotEmpty(whereSql)){
            sql+=" where "+whereSql;
        }
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    public long nextVal(String sequenceName){
        String sql="select nextval('"+sequenceName+"')";
        return jdbcTemplate.queryForObject(sql,Long.class);
    }
}

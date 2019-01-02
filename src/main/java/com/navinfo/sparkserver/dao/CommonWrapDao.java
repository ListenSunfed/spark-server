/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.dao
 * Author: wulongyue06158
 * Date: Created in 2019/1/2 16:15
 **************************************/
package com.navinfo.sparkserver.dao;

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.backupservice.dao
 * Author: wulongyue06158
 * Date: Created in 2018/9/4 16:49
 **************************************/

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/*************************************
 * Class Name: CommonWrapDao
 * Description:〈commonDao装饰类〉
 * @author wulongyue
 * @create 2018/9/4
 * @since 1.0.0
 ************************************/
public class CommonWrapDao<T> extends CommonDao {

    private Class<T> clazz;

    protected CommonWrapDao() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    protected List<T> listAll(String sql) {
        List<T> list = jdbcTemplate.query(sql,
                new RowMapper<T>() {
                    @Nullable
                    @Override
                    public T mapRow(ResultSet resultSet, int i) throws SQLException {
                        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                        int columnCount = resultSetMetaData.getColumnCount();
                        T entity = null;
                        try {
                            entity = clazz.newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        for (int n = 1; n <= columnCount; n++) {
                            String columnName = resultSetMetaData.getColumnName(n);
                            Object value = resultSet.getObject(columnName);
                            try {
                                BeanUtils.setProperty(entity, columnName, value);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                        return entity;
                    }
                });
        return list;
    }
}

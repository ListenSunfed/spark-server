package com.navinfo.sparkserver.dao;

import com.navinfo.sparkserver.model.hbase.ImportRecord;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "importRecordDao")
public class ImportRecordDao {
    @Value("${spring.datasource.table.t_data_import_record}")
    String t_data_import_record;

    @Resource(name="jdbcTemplate2")
    JdbcTemplate jdbcTemplate;

    /**
     * 获得所有需要导入的数据
     * @return
     */
    public List<ImportRecord> getAllRecord(){
        String sql = String.format(
                "select * from %s " +
                "where status=2 and error_type>-1 ",
                t_data_import_record
        );
        List<ImportRecord> list = jdbcTemplate.query(sql,
                new RowMapper<ImportRecord>() {
                    @Nullable
                    @Override
                    public ImportRecord mapRow(ResultSet resultSet, int i) throws SQLException {
                        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                        int columnCount = resultSetMetaData.getColumnCount();
                        ImportRecord entity = new ImportRecord();
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

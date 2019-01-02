/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.datalaker.ADASMapService.config
 * Author: liumingkai05559
 * Date: Created in 2018/3/24 10:41
 **************************************/
package com.navinfo.sparkserver.config;

import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HConstants;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;


/*************************************
 * Class Name: RootConfig
 * Description:〈启动配置〉
 * @author liumingkai
 * @create 2018/3/24
 * @since 1.0.0
 ************************************/
@Configuration
public class RootConfig {
    @Autowired
    Environment env;

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        try{
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
            dataSource.setUrl(env.getProperty("spring.datasource.url"));
            dataSource.setUsername(env.getProperty("spring.datasource.username"));
            dataSource.setPassword(env.getProperty("spring.datasource.password"));
            dataSource.setMinIdle(10);
            dataSource.setMaxIdle(100);
            dataSource.setInitialSize(10);
            dataSource.setMaxActive(100);
            jdbcTemplate.setDataSource(dataSource);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jdbcTemplate;
    }

//    @Bean(name = "hbaseConnection")
//    public Connection getHBaseConnection(){
//        Connection connection = null;
//        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
//        config.set(HConstants.ZOOKEEPER_QUORUM, env.getProperty("hbase.zookeeper"));
//        try{
//            connection = ConnectionFactory.createConnection(config);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    @Bean(name = "esClient")
//    public RestHighLevelClient getElasticsearchClient(){
//        String ips=env.getProperty("es.cluster.ips");
//        String[] ipArr=ips.split(",");
//        HttpHost[] httpHosts=new HttpHost[ipArr.length];
//        for(int i=0;i<ipArr.length;i++){
//            httpHosts[i]=new HttpHost(ipArr[i],9200,"http");
//        }
//        RestClientBuilder builder =RestClient.builder(httpHosts);
//        return new RestHighLevelClient(builder);
//    }
}

package com.navinfo.sparkserver.model.hbase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImportRecord {
    /**
    *
    */
    private String project_id;

    /**
    *
    */
    private String channel_code;

    /**
    *
    */
    private String create_time;

    /**
    *
    */
    private String status;

    /**
    *
    */
    private String error_type;

    /**
    *
    */
    private int total_num;
}

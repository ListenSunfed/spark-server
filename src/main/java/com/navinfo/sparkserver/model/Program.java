package com.navinfo.sparkserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Program {
    /**
    *   程序名
    */
    private String procedureName;

    /**
    * 程序主类
    */
    private String procedureMain;

    /**
    * 程序路径
    */
    private String procedurePath;

    /**
    * 程序描述
    */
    private String description;

    /**
    * 程序参数
    */
    private String args;

    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 跟新时间
    */
    private String updateTime;
}

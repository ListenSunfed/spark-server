package com.navinfo.sparkserver.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor()
@NoArgsConstructor
public class Program {
    /**
    *   程序名
    */
    private String programName;

    /**
    * 程序主类
    */
    private String programMain;

    /**
    * 程序路径
    */
    private String programPath;

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

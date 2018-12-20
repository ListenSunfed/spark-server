/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkmanange.model
 * Author: wulongyue06158
 * Date: Created in 2018/12/20 16:14
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/*************************************
 * Class Name: Statement
 * Description:〈任务状态〉
 * @author wulongyue
 * @create 2018/12/20
 * @since 1.0.0
 ************************************/
@Getter
@Setter
@ToString
public class Statement {

    /**
     *当前任务的id
     */
    private String id;

    /**
     *指令代码
     */
    private String code;

    /**
     *当前任务状态
     */
    private String state;

    private Output output;
}

@Getter
@Setter
@ToString
class Output {

    /**
     * 当前任务结果状态
     */
    private String status;

    private Data data;

}

@Getter
@Setter
@ToString
class Data {
    private ApplicationJson applicationJson;
}

@Getter
@Setter
@ToString
class ApplicationJson {
    private Schema schema;
    private String[][] data;
}

@Getter
@Setter
@ToString
class Schema {

    /**
     * struct
     */
    private String type;

    private List<Field> fields;
}

@Getter
@Setter
@ToString
class Field {

    /**
     *  字段名
     */
    private String name;

    /**
     *  字段类型
     */
    private String type;

    /**
     *是否可以为null
     */
    private boolean nullable;

    private Metadata metadata;

}

@Getter
@Setter
@ToString
class Metadata {

    /**
     * hive中字段的类型
     */
    private String HIVE_TYPE_STRING;
}
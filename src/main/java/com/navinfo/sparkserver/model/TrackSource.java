/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model
 * Author: wulongyue06158
 * Date: Created in 2019/1/2 16:21
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Data;
import lombok.ToString;

/*************************************
 * Class Name: TrackSource
 * Description:〈导入数据的初始轨迹信息〉
 * @author wulongyue
 * @create 2019/1/2
 * @since 1.0.0
 ************************************/
@Data
@ToString
public class TrackSource {

    /**
     * 轨迹id
     */
    private String projectId;

    /**
     * 轨迹状态
     */
    private int status;

    /**
     * 错误类型
     */
    private int errType;

    /**
     * 算法id
     */
    private String algorithmCode;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 文件绝对路径
     */
    private String filePath;

    /**
     * 轨迹串类型
     */
    private String type;
}


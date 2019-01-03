/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model
 * Author: wulongyue06158
 * Date: Created in 2019/1/3 11:01
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Data;
import lombok.ToString;

/*************************************
 * Class Name: BatchesResponse
 * Description:〈batches状态〉
 * @author wulongyue
 * @create 2019/1/3
 * @since 1.0.0
 ************************************/
@Data
@ToString
public class BatchesResponse {

    /**
     *
     */
    private String batchId;

    /**
     *
     */
    private String state;

    /**
     *
     */
    private String appId;

    /**
     *
     */
    private String driverLogUrl;

    /**
     *
     */
    private String sparkUiUrl;
}

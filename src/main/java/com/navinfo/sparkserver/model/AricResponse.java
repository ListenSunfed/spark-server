/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 16:13
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************
 * Class Name: AricResponse
 * Description:〈〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/
@Getter
@Setter
@ToString
public class AricResponse {
    private String responseMessage;

    public AricResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 15:47
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************
 * Class Name: RequestMessage
 * Description:〈请求消息〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/
@Getter
@Setter
@ToString
public class RequestMessage {
    private String name;
    private String id;

    public RequestMessage() {
    }

    public RequestMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.model
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 16:14
 **************************************/
package com.navinfo.sparkserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************
 * Class Name: AricMessage
 * Description:〈〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/
@Getter
@Setter
@ToString
public class AricMessage {
    private String name;

    public String getName() {
        return name;
    }
}

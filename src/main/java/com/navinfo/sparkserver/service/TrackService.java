package com.navinfo.sparkserver.service;

import java.util.List;

public interface TrackService {

    /**
     * 获得需要导入的轨迹串信息
     * @return
     */
    List<String> getTracks();
}

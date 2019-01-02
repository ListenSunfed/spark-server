package com.navinfo.sparkserver.service;

import com.navinfo.sparkserver.model.TrackSource;

import java.util.List;

public interface TrackService {

    /**
     * 获得需要导入的轨迹串信息
     * @return
     */
    List<TrackSource> getTracks();
}

/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.service.impl
 * Author: wulongyue06158
 * Date: Created in 2019/1/2 18:24
 **************************************/
package com.navinfo.sparkserver.service.impl;

import com.navinfo.sparkserver.dao.TrackDao;
import com.navinfo.sparkserver.model.TrackSource;
import com.navinfo.sparkserver.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*************************************
 * Class Name: TrackServiceImpl
 * Description:〈〉
 * @author wulongyue
 * @create 2019/1/2
 * @since 1.0.0
 ************************************/
@Service("trackService")
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackDao trackDao;

    @Override
    public List<TrackSource> getTracks() {
        List<TrackSource> trackSources = trackDao.listAllTrack();
        return trackSources;
    }
}

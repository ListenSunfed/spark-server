package com.navinfo.sparkserver.service.impl;

import com.navinfo.sparkserver.dao.ImportRecordDao;
import com.navinfo.sparkserver.model.hbase.ImportRecord;
import com.navinfo.sparkserver.service.ImportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("importRecordService")
public class ImportRecordImpl implements ImportRecordService {

    @Autowired
    private ImportRecordDao importRecordDao;

    @Override
    public List<ImportRecord> getRecords() {
        List<ImportRecord> allRecord = importRecordDao.getAllRecord();
        return allRecord;
    }
}

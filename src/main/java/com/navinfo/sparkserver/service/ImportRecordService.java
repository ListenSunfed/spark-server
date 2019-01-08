package com.navinfo.sparkserver.service;

import com.navinfo.sparkserver.model.hbase.ImportRecord;

import java.util.List;

public interface ImportRecordService {
    List<ImportRecord> getRecords();
}

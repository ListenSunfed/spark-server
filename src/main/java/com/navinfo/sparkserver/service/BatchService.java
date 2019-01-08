package com.navinfo.sparkserver.service;

import com.navinfo.sparkserver.model.BatchesMessage;
import com.navinfo.sparkserver.model.BatchesResponse;

import java.util.List;

public interface BatchService {

    String submitBatch(BatchesMessage message);

    BatchesResponse getBatchInfo(String batchId);

    List<BatchesResponse> getBatchInfos();
}

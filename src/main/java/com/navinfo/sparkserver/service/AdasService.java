package com.navinfo.sparkserver.service;

import com.navinfo.sparkserver.model.BatchesMessage;
import com.navinfo.sparkserver.model.BatchesResponse;

public interface AdasService {

    String submitAdas(BatchesMessage message);

    BatchesResponse getBatchInfo(String batchId);

    void loop();
}

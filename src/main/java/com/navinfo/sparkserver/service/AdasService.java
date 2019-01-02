package com.navinfo.sparkserver.service;

public interface AdasService {

    String submitAdas(String jarPath,String projectName,String className,String args,String queue, String driverMemory, String executorMemory, String driverCores, String numExecutors, String executorCores);
}

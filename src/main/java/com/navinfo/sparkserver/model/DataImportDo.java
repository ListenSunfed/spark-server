package com.navinfo.sparkserver.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/****************************
 * Class Name： DataImportDo
 * Description： 导入数据实体
 * @Author: sunmeina
 * @create: 12/14/2018
 * @since: 1.0.0
 ***************************/
@Repository(value="dataImportDo")
@Slf4j
public class DataImportDo {

    /**
     * 轨迹串id
     */
    private String projectId;

    /**
     * 状态【0:未导入,1:导入中,2:导入完成，3分段中,4分段成功,5分段出错,6算子错误,7:算子成功结束】
     */
    private int status;

    /**
     * 错误类型:【0:没有问题,-1:跳点问题,-2:整个线程出现问题】
     */
    private int errType;

    /**
     * 已完成算法code，参见枚举类 AlgorithmEnums
     */
    private String algorithmCode;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrType() {
        return errType;
    }

    public void setErrType(int errType) {
        this.errType = errType;
    }

    public String getAlgorithmCode() {
        return algorithmCode;
    }

    public void setAlgorithmCode(String algorithmCode) {
        this.algorithmCode = algorithmCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}

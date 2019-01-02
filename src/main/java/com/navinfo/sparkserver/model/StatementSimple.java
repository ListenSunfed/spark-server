package com.navinfo.sparkserver.model;

public class StatementSimple {
    /**
     *当前任务的id
     */
    private String id;

    /**
     *指令代码
     */
    private String code;

    /**
     *当前任务状态
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public StatementSimple(String id, String code, String state) {
        this.id = id;
        this.code = code;
        this.state = state;
    }

    public StatementSimple() {
    }
}

package com.navinfo.sparkserver.service;

import com.navinfo.sparkserver.model.Program;

import java.util.List;

public interface ProgramService {

    /**
     * 注册一个新的程序信息
     *
     * @param program
     */
    int registPragram(Program program);

    /**
     * 更新一个新的程序信息
     *
     * @param program
     */
    int updateProgram(Program program);

    /**
     * 删除程序信息
     *
     * @param programName
     */
    void delProgram(String programName);

    /**
     * 获取所有的程序信息
     * @return
     */
    List<Program> getPrograms();

    /**
     * 根据程序名查找具体信息
     * @param programName
     * @return
     */
    Program getProgram(String programName);
}

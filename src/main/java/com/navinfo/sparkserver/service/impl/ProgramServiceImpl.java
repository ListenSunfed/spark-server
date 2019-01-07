package com.navinfo.sparkserver.service.impl;

import com.navinfo.sparkserver.dao.ProgramDao;
import com.navinfo.sparkserver.model.Program;
import com.navinfo.sparkserver.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("programService")
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramDao programDao;

    @Override
    public int registPragram(Program program) {
        return programDao.addProgram(program);
    }

    @Override
    public int updateProgram(Program program) {
        return programDao.updateProgram(program);
    }

    @Override
    public void delProgram(String programName) {
        programDao.delProgram(programName);
    }

    @Override
    public List<Program> getPrograms() {
        List<Program> programs = programDao.getPrograms();
        return programs;
    }

    @Override
    public Program getProgram(String programName) {
        Program program = programDao.getProgram(programName);
        return program;
    }
}

package com.navinfo.sparkserver.service.impl;

import com.navinfo.sparkserver.dao.ProgramDao;
import com.navinfo.sparkserver.model.Program;
import com.navinfo.sparkserver.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("programService")
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramDao programDao;

    @Override
    public void registPragram(Program program) {
        programDao.addProgram(program);
    }
}

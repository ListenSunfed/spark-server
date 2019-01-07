package com.navinfo.sparkserver.dao;

import com.navinfo.sparkserver.model.Program;
import org.springframework.stereotype.Repository;

/*************************************
 * Class Name: TrackDao
 * Description:〈处理程序的存储与查询〉
 * @author wulongyue
 * @create 2019/1/6
 * @since 1.0.0
 ************************************/
@Repository(value="programDao")
public class ProgramDao extends CommonWrapDao<Program> {

    public void addProgram(Program program) {

    }
}

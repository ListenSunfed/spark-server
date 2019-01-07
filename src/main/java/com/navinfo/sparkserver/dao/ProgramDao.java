package com.navinfo.sparkserver.dao;

import com.navinfo.sparkserver.controller.ProgramController;
import com.navinfo.sparkserver.model.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/*************************************
 * Class Name: TrackDao
 * Description:〈处理程序的存储与查询〉
 * @author wulongyue
 * @create 2019/1/6
 * @since 1.0.0
 ************************************/
@Repository(value = "programDao")
public class ProgramDao extends CommonWrapDao<Program> {

    private static final Logger logger = LoggerFactory.getLogger(ProgramDao.class);

    @Value("${spring.datasource.table.t_program}")
    String t_program;


    /**
     * pg库中添加一条程序信息
     *
     * @param program
     * @return
     */
    public int addProgram(Program program) {
        int result = -1;
        String sql = String.format("insert into %s (program_name,program_main,program_path,description,args,create_time,update_time) " +
                        "VALUES('%s','%s','%s','%s','%s','%s','%s')",
                t_program,
                program.getProgramName(),
                program.getProgramMain(),
                program.getProgramPath(),
                program.getDescription(),
                program.getArgs(),
                program.getCreateTime(),
                program.getUpdateTime()
        );
        logger.info(String.format("添加一条程序信息，sql：%s", sql));
        result = jdbcTemplate.update(sql);
        return result;
    }

    /**
     * 更新程序信息
     *
     * @param program
     * @return
     */
    public int updateProgram(Program program) {
        String sql = String.format("update %s " +
                        "set " +
                        "program_main = '%s', " +
                        "program_path = '%s'," +
                        "description = '%s'," +
                        "args = '%s'," +
                        "update_time = '%s' " +
                        "where program_name = '%s';",
                t_program,
                program.getProgramMain(),
                program.getProgramPath(),
                program.getDescription(),
                program.getArgs(),
                program.getUpdateTime(),
                program.getProgramName()
        );
        logger.info(String.format("更新程序信息，sql：%s", sql));
        int result = jdbcTemplate.update(sql);
        return result;
    }

    /**
     * 删除一个程序的信息
     *
     * @param programName
     */
    public void delProgram(String programName) {
        String sql = String.format("delete from %s where program_name = '%s';",
                t_program, programName);
        logger.info(String.format("删除一条程序信息，sql：%s", sql));
        jdbcTemplate.execute(sql);
    }

    /**
     * 获得所有程序信息
     *
     * @return
     */
    public List<Program> getPrograms() {
        String sql = String.format("select " +
                "program_name as \"programName\"," +
                "program_main as \"programMain\"," +
                "program_path as \"programPath\"," +
                "description," +
                "args," +
                "create_time as \"createTime\"," +
                "update_time as \"updateTime\" " +
                "from %s;", t_program);
        logger.info(String.format("查找所有程序信息，sql：%s", sql));
        List<Program> programs = listAll(sql);
        return programs;
    }

    /**
     * 根据程序名查找具体信息
     *
     * @param programName
     * @return
     */
    public Program getProgram(String programName) {
        Program program = null;
        String sql = String.format("select " +
                "program_name as \"programName\"," +
                "program_main as \"programMain\"," +
                "program_path as \"programPath\"," +
                "description," +
                "args," +
                "create_time as \"createTime\"," +
                "update_time as \"updateTime\" " +
                "from %s where program_name = '%s';", t_program, programName);
        logger.info(String.format("根据%s程序信息，sql：%s", programName, sql));
        program = getBean(sql);
        return program;
    }

}

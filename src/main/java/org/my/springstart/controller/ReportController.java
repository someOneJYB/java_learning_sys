package org.my.springstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.ClazzOption;
import org.my.springstart.entity.JobOption;
import org.my.springstart.entity.Result;
import org.my.springstart.service.iml.ReportServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportServiceIml reportService;

    /**
     * 统计各职位员工人
     */
    @GetMapping("/empJobData")
    public Result empJobData(){
        log.info("统计各职位员工人数...");
        JobOption jobOption = reportService.empJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计各性别员工人数
     */
    @GetMapping("/empGenderData")
    public Result empGenderData(){
        log.info("统计各性别员工人数...");
        List<Map> list = reportService.empGenderData();
        return Result.success(list);
    }
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("班级人数统计");
        ClazzOption clazzCountOption = reportService.getStudentCountData();
        return Result.success(clazzCountOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计各性别学生学历...");
        List<Map> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }
}

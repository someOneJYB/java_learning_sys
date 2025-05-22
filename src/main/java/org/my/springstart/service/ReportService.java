package org.my.springstart.service;

import org.my.springstart.entity.ClazzOption;
import org.my.springstart.entity.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各职位员工人数
     * @return
     */
    JobOption empJobData();

    /**
     * 统计各性别员工人数
     * @return
     */
    List<Map> empGenderData();
//    List<Map> studentCountData();
    ClazzOption getStudentCountData();
    List<Map>getStudentDegreeData();
}

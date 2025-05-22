package org.my.springstart.service.iml;

import org.my.springstart.entity.ClazzOption;
import org.my.springstart.entity.JobOption;
import org.my.springstart.mapper.EmployeeMapper;
import org.my.springstart.mapper.StudentMapper;
import org.my.springstart.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceIml implements ReportService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption empJobData() {
        List<Map> list = employeeMapper.countByJob();

        // List jobList = new ArrayList();
        // for (Map map : list) {
        //     jobList.add(map.get("pos"));
        // }
        //1.获取jobList职位列表
        List jobList = list.stream().map(item -> item.get("pos")).toList();

        //2.获取dataList各职位总人数列表
        List dataList = list.stream().map(item -> item.get("sum")).toList();

        //3.封装JobOption对象，并返回
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> empGenderData() {
        //调用mapper，获取员工性别数据
        System.out.println("***************************");
        System.out.println(employeeMapper.countByGender());
        System.out.println("***************************");
        return employeeMapper.countByGender();
    }
    @Override
    public List<Map>getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> countList = studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            List<Object> clazzList = countList.stream().map(map -> {
                return map.get("cname");
            }).toList();

            List<Object> dataList = countList.stream().map(map -> {
                return map.get("scount");
            }).toList();

            return new ClazzOption(clazzList, dataList);
        }
        return null;
    }
}

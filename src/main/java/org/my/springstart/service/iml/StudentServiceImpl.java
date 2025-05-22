package org.my.springstart.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.my.springstart.entity.Clazz;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.PageBean;
import org.my.springstart.entity.Student;
import org.my.springstart.mapper.StudentMapper;
import org.my.springstart.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageBean list(EmpQueryParam param) {
        //1.设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        //2.调用mapper的列表查询方法
        List<Student> empList = studentMapper.list(param);
        // page 为 sql 添加拦截器增加 count(*) 和 limit 查询总数和分页
        Page p = (Page) empList;        //强转对象，Page继承了ArrayList

        //3.封装PageBean对象并返回
        return new PageBean(p.getTotal(), p.getResult());
    };
    @Override
    public Integer getClass(Integer id) {
        return studentMapper.getClass(id);
    }
    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }
    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void updateScore(Integer id, Integer score) {
        Student student = new Student();
        student.setId(id);
                student.setViolationScore(Short.valueOf(String.valueOf(score)));
        studentMapper.update(student);
    }
    @Override
    public void delete(Integer id) {
        studentMapper.delete(id);
    }
}

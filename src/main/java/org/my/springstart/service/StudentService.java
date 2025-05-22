package org.my.springstart.service;

import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.PageBean;
import org.my.springstart.entity.Student;

public interface StudentService {
    PageBean list(EmpQueryParam param);
    Integer getClass(Integer id);
    void add(Student student);
    Student getById(Integer id);
    void update(Student student);
    void updateScore(Integer id, Integer score);
    void delete(Integer id);
}

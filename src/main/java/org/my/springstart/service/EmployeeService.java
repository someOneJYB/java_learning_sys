package org.my.springstart.service;

import org.my.springstart.entity.*;

import java.util.List;

public interface EmployeeService {
    PageBean page(EmpQueryParam param);
    void delete(Integer id);
    void add(Employee employee) throws Exception;
    Employee get(Integer id);
    void update(Employee employee);
    List<Employee> getAll();
    EmpLoginInfo login(Employee employee);
}

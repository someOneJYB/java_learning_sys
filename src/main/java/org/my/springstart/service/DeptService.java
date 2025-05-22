package org.my.springstart.service;


import org.my.springstart.entity.Dept;

import java.time.LocalDateTime;
import java.util.List;

public interface DeptService {
    public List<Dept> list();
    public List<Dept> listSql();
    public void delete(Integer id);

    void insert(String name);
    void update(Dept dept);
    public Dept getById(Integer id);
}

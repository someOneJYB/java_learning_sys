package org.my.springstart.service;

import org.my.springstart.entity.Clazz;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.PageBean;

import java.util.List;

public interface ClazzService {
    PageBean list(EmpQueryParam param);
    void add(Clazz clazz);

    void update(Clazz clazz);

    Clazz getById(Integer id);
    void deleteById(Integer id) throws Exception;
    List<Clazz> getAll();
}

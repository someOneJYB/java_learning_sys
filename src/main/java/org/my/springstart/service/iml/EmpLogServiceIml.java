package org.my.springstart.service.iml;

import lombok.Data;
import org.my.springstart.entity.EmpLog;
import org.my.springstart.mapper.EmpLogMapper;
import org.my.springstart.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceIml implements EmpLogService {
    @Autowired
    private EmpLogMapper empLogMapper;

    // @Transactional(propagation = Propagation.REQUIRED)  //开启事务，REQUIRED默认值，有则加入，无则创建
    @Transactional(propagation = Propagation.REQUIRES_NEW)  //开启事务, 开启新事务
    @Override
    public void insertEmpLog(EmpLog empLog) {
        System.out.println("start insertLog");
        System.out.println(empLog);
        empLogMapper.insert(empLog);
    }
}

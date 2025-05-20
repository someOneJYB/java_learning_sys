package org.my.springstart.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.my.springstart.entity.EmpLog;

@Mapper
public interface EmpLogMapper {
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    void insert(EmpLog empLog);
}

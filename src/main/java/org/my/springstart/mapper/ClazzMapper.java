package org.my.springstart.mapper;

import org.apache.ibatis.annotations.*;
import org.my.springstart.entity.Clazz;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.Employee;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(EmpQueryParam param);
    @Insert("insert into clazz values (null, #{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, now(), now())")
    void add(Clazz clazz);
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);
    void update(Clazz clazz);
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
    @Select("select * from clazz")
    List<Clazz> getAll();
}

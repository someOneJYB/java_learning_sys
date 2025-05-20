package org.my.springstart.mapper;
import org.apache.ibatis.annotations.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.my.springstart.entity.EmpLoginInfo;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.Employee;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {
    List<Employee> list(EmpQueryParam param);
    @Delete("delete from emp where id = #{id}")
    void delete(Integer id);
    void add(Employee employee);
    @Select("select * from emp where id = #{id}")
    Employee get(Integer id);
    @Select("select * from emp")
    List<Employee> getAll();
    void update(Employee employee);
    Employee getJoin(Integer id);
    /**
     * 统计各职位员工人数
     * @return
     */
    @MapKey("pos")  //可以不加，不影响业务...
    List<Map> countByJob();

    @MapKey("name")
    List<Map> countByGender();
    EmpLoginInfo getLogin(Employee employee);
}

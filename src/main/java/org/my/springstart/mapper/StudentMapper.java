package org.my.springstart.mapper;

import org.apache.ibatis.annotations.*;
import org.my.springstart.entity.ClazzOption;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(EmpQueryParam empQueryParam);
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer getClass(Integer id);
    void add(Student student);
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);
    void update(Student student);
    @Delete("delete  from student where id = #{id}")
    void delete(Integer id);
    @Select("select c.name cname , count(s.id) scount from clazz c  left join student s on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    List<Map<String,Object>> getStudentCount();
    List<Map> getStudentDegreeData();
}

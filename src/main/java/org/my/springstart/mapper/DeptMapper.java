package org.my.springstart.mapper;

import org.apache.ibatis.annotations.*;
import org.my.springstart.entity.Dept;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询部门列表
     * @return
     */
    // 数据封装方式一：手动结果映射,和实体的属性进行映射
     @Results({
             @Result(column = "create_time", property = "createTime"),
             @Result(column = "update_time", property = "updateTime")
     })

    // 数据封装方式二：起别名
     // 如果配置的驼峰转化，就不用起别名，查询以后字段就转化成 Dept create_time字段实体中的 createTime，这个方式就自动对应实体对象中的属性
    // @Select("select id, name, create_time createTime, update_time updateTime from dept")

    // 数据封装方式三：全局配置，开启驼峰命名规则映射
    @Select("select * from dept")
    public List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    public void delete(Integer id);

    @Insert("insert into dept values (null, #{name}, now(), now())")
    public void insert(String name);

//    @Update("update dept set name=#{name},create_time=#{createTime}, update_time=#{updateTime} where id = #{id}")
//    public void update(Dept dept); 使用动态 sql 有时间就更新，否则就不更新，只更新 name 字段

    @Select("select * from dept where id = #{id}")
    public Dept getById(Integer id);


    public void update(Dept dept);
}

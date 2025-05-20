package org.my.springstart.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.my.springstart.entity.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工经历数据
     * @param exprList
     */
    //基于XML开发-动态SQL--<foreach>
    void insertBatch(List<EmpExpr> exprList);
    @Select("select * from emp_expr where emp_id = #{empId}")
    List<EmpExpr> list(Integer empId);
    @Delete("delete from emp_expr where emp_id = #{empId}")
    void deleteById(Integer empId);
}

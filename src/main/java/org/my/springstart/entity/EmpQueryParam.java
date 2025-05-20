package org.my.springstart.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {

    private Integer page = 1;       //页码
    private Integer pageSize = 10;  //每页展示记录数
    private String name;            //姓名
    private Integer gender;         //性别
    // 接收日期参数类型的注解 @DateTimeFormat
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;        //入职开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;          //入职结束时间
    private Integer clazzId;
    private Integer degree;
}

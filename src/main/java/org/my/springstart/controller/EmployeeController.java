package org.my.springstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.*;
import org.my.springstart.mapper.EmpExprMapper;
import org.my.springstart.service.EmpLogService;
import org.my.springstart.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/emps")
@Component
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpExprMapper empExprMapper;

    @GetMapping("/list")
    public Result list() {
        return Result.success(employeeService.getAll());
    }

    @GetMapping
    public Result page(EmpQueryParam param){
        log.info("分页查询：{}, {}, {},{}, {}, {}", param.getName(), param.getGender(), param.getBegin(), param.getEnd(), param.getPage(), param.getPageSize());
        PageBean pageBean = employeeService.page(param);
        return Result.success(pageBean);
    }
    @Transactional
    @DeleteMapping
    //emps?id=90或者emps?id=90,69,30，批量删除Integer[] ids 接收或者 @RequestParam List<Integer> ids
    public Result delete(@RequestParam List<Integer> ids) {
        System.out.println(ids + "90999");
        ids.forEach(id -> {
            employeeService.delete(id);
            // 删除对应的工作经历表
            empExprMapper.deleteById(id);
        });
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Employee employee) throws Exception {
        employeeService.add(employee);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result update(@PathVariable Integer id) {
        return Result.success(employeeService.get(id));
    }
    @PutMapping
    public Result update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return Result.success();
    }

}

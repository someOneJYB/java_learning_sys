package org.my.springstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.Clazz;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.PageBean;
import org.my.springstart.entity.Result;
import org.my.springstart.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clazzs")
@Slf4j
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @GetMapping
    public Result page(EmpQueryParam param){
        log.info("分页查询：{}, {}, {},{}, {}, {}", param.getName(), param.getGender(), param.getBegin(), param.getEnd(), param.getPage(), param.getPageSize());
//        PageBean pageBean = employeeService.page(param);
        return Result.success(clazzService.list(param));
    }
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("分页查询：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }
    @GetMapping("/list")
    public Result getAll(){
//        PageBean pageBean = employeeService.page(param);
        return Result.success(clazzService.getAll());
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("分页查询：{}", id);
//        PageBean pageBean = employeeService.page(param);
        return Result.success(clazzService.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) throws Exception {
        log.info("分页查询：{}", id);
//        PageBean pageBean = employeeService.page(param);
        clazzService.deleteById(id);
        return Result.success();
    }


}

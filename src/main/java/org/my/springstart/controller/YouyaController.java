package org.my.springstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.Dept;
import org.my.springstart.entity.Result;
import org.my.springstart.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/youyas")
@Component
public class YouyaController {
    @Autowired
    private DeptService deptService;

//    路径是 /depts
//    @GetMapping
//    public Result getAll() throws IOException {
//        return Result.success(deptService.list());
//    }
    @GetMapping
    public Result getList() throws IOException {
        System.out.println(deptService.listSql());
        return Result.success(deptService.listSql());
    }

    // /depts?id=90
    @DeleteMapping
    public Result deleteDept(@RequestParam("id") Integer deptId) {
        System.out.println(deptId);
        deptService.delete(deptId);
        return Result.success();
    }
    // /depts/30
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        System.out.println(id + "id");
        //调用service的方法
        Dept dept = deptService.getById(id);
        System.out.println(dept);
        return Result.success(dept);
    }
    // /depts 参数在 body 中
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("dept = {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}

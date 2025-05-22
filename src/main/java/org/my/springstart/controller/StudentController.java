package org.my.springstart.controller;

import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.Result;
import org.my.springstart.entity.Student;
import org.my.springstart.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result list(EmpQueryParam empQueryParam) {
        return Result.success(studentService.list(empQueryParam));
    }
    @PostMapping
    public Result add(@RequestBody Student student) {
        studentService.add(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(studentService.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Student student) {
        studentService.update(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result updateScore(@PathVariable Integer id, @PathVariable Integer score) {
        studentService.updateScore(id, score);
        return Result.success();
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        ids.forEach(id -> {
            studentService.delete(id);
        });
        return Result.success();
    }


//    @DeleteMapping
//    public Result delete(@RequestParam List<Integer> ids) {
//        System.out.println(ids + "8888888888888");
//        ids.forEach(id -> {
//            studentService.delete(id);
//        });
//        return Result.success();
//    }
}

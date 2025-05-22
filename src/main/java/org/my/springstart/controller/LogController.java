package org.my.springstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.PageBean;
import org.my.springstart.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @GetMapping("/page")
    public Result page(EmpQueryParam p) {
        log.info("{}", p);
        return Result.success();
    }
}

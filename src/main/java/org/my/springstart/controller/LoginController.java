package org.my.springstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.EmpLoginInfo;
import org.my.springstart.entity.Employee;
import org.my.springstart.entity.Result;
import org.my.springstart.service.EmployeeService;
import org.my.springstart.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param emp
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Employee emp){
        log.info("员工登录：{}", emp);
        EmpLoginInfo loginInfo = employeeService.login(emp);
        if (loginInfo == null) {
            return Result.error("用户名或密码错误！！");
        }
        System.out.println(loginInfo);
        return Result.success(loginInfo);
    }
}

package org.my.springstart.controller;

import org.apache.commons.io.IOUtils;
import org.my.springstart.entity.Dept;
import org.my.springstart.entity.Result;
import org.my.springstart.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Lazy       //延迟加载，使用的时候才会创建构造函数
//@Scope("prototype") //设置作用域，默认是singleton（单例）,prototype（非单例）
@Slf4j
@RestController
//@Controller
//@ResponseBody
@Component
public class DeptController {
    //@Resource(name = "deptServiceImpl")
@Autowired
private DeptService deptService;
//@Qualifier("deptServiceImpl") 或者 @Autowired + @Qualifier 注入指定的，bean 类首字母小写
//为了测试，检查bean对象的创建时机
public DeptController(){
    System.out.println("DeptController....创建....");
}


    private Dept currentDept;

    @GetMapping("/dept")
    public Result getAll() throws IOException {
//        // 只能识别 resource 里面的资源文件，想要读取外界的需要使用 FileReader 的文件
//        InputStream ip = this.getClass().getClassLoader().getResourceAsStream("dep.txt");
//        List<String> l = IOUtils.readLines(ip, "utf-8");
        // 获取所有的部门信息
//        List<Dept> list = l.stream().map(i -> {
//            String[] split = i.split(",");
//            Integer deptId = Integer.valueOf(split[0]);
//            String deptName = split[1];
//            LocalDateTime deptUpdateTime = LocalDateTime.parse(split[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new Dept(deptId, deptName, deptUpdateTime);
//        }).toList();
//        return list;
        return Result.success(deptService.list());
    }


    @GetMapping("/depts")
    public Result getList() throws IOException {
//        // 只能识别 resource 里面的资源文件，想要读取外界的需要使用 FileReader 的文件
//        InputStream ip = this.getClass().getClassLoader().getResourceAsStream("dep.txt");
//        List<String> l = IOUtils.readLines(ip, "utf-8");
        // 获取所有的部门信息
//        List<Dept> list = l.stream().map(i -> {
//            String[] split = i.split(",");
//            Integer deptId = Integer.valueOf(split[0]);
//            String deptName = split[1];
//            LocalDateTime deptUpdateTime = LocalDateTime.parse(split[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new Dept(deptId, deptName, deptUpdateTime);
//        }).toList();
//        return list;
        System.out.println(deptService.listSql());
        return Result.success(deptService.listSql());
    }

// 使用
//    @RequestMapping(value = "/depts", method = RequestMethod.POST)     //限制请求方式为Get
    @PostMapping
    public Result getAllDepts(){
        //1.加载并读取dept.txt文件
        //通过类加载器，可以获取到类路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dep.txt");
        List<String> strings = IOUtils.readLines(input, "UTF-8");

        //2.解析文本中的数据，并将其封装成集合List<Dept>
        List<Dept> depts =  strings.stream().map((str)->{
            String[] parts = str.split(",");
            Integer id = Integer.valueOf(parts[0]);
            String name = parts[1];
            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime, updateTime);
        }).toList();

        //3.响应数据（json格式）
        return Result.success(depts);
    }

    @DeleteMapping("/depts")
    public Result deleteDept(@RequestParam("id") Integer deptId) {
        System.out.println(deptId);
        deptService.delete(deptId);
        return Result.success();
    }
    @PostMapping("/depts")
    public Result updateDept(@RequestBody Dept dept) {
        System.out.println(dept);
        System.out.println("longing" + dept.getName());
        deptService.insert(dept.getName());
        return Result.success();
    }
    // 对于路径是 /depts/1 这种，路径参数：通过请求URL直接传递参数，使用{…}来标识该路径参数，需要使用 @PathVariable 获取路径参数。多个参数就用 @PathVariable 作为修饰获取
    // @GetMapping("/depts/{id}/{sta}")
    // public Result getInfo(@PathVariable Integer id, @PathVariable Integer sta)
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        System.out.println(id + "id");
        //调用service的方法
        Dept dept = deptService.getById(id);
        currentDept = dept;
        System.out.println(dept);
        return Result.success(dept);
    }
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("dept = {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}

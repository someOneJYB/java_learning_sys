package org.my.springstart.service.iml;

import org.my.springstart.aop.Log;
import org.my.springstart.dao.DeptDao;
import org.my.springstart.dao.DeptDaoImpl;
import org.my.springstart.entity.Dept;
import org.my.springstart.mapper.DeptMapper;
import org.my.springstart.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 业务逻辑处理层
 */
@Component
// @Component  //程序启动时，会自动创建该类对象，并交由IOC容器管理
// @Primary       //指定该bean优先级最高
@Service
public class DeptServiceImpl implements DeptService {

    // private DeptDao deptDao = new DeptDaoImpl();
    @Autowired  //从IOC容器中，自动寻找bean对象，为该变量赋值---依赖注入DI的实现
    private DeptDao deptDao = new DeptDaoImpl();

    public List<Dept> list(){
        //1.获取原始数据
        List<String> stringList = deptDao.list();

        //2.处理数据--将数据封装成List<Dept>
        //2.解析文本中的数据，并将其封装成集合List<Dept>
        List<Dept> depts =  stringList.stream().map((str)->{
            String[] parts = str.split(",");
            Integer id = Integer.valueOf(parts[0]);
            String name = parts[1];
            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime, updateTime);
        }).toList();

        //3.返回封装好的数据
        return depts;
    }

    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> listSql(){
        //1.调用mapper的方法，获取列表数据并返回
        return deptMapper.list();
    }

    @Log
    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

   @Override
   public void insert(String name) {
        deptMapper.insert(name);
   }

   @Override
   public void update(Dept dept) {
        System.out.println(dept);
        deptMapper.update(dept);
   }
    @Override
    public Dept getById(Integer id) {
        //调用mapper的查询方法
        return deptMapper.getById(id);
    }
}

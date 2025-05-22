package org.my.springstart.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.my.springstart.entity.*;
import org.my.springstart.mapper.EmpExprMapper;
import org.my.springstart.mapper.EmployeeMapper;
import org.my.springstart.service.EmpLogService;
import org.my.springstart.service.EmployeeService;
import org.my.springstart.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;


    @Override
    public PageBean page(EmpQueryParam param) {
        //1.设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        //2.调用mapper的列表查询方法
        List<Employee> empList = employeeMapper.list(param);
        // page 为 sql 添加拦截器增加 count(*) 和 limit 查询总数和分页
        Page p = (Page) empList;        //强转对象，Page继承了ArrayList

        //3.封装PageBean对象并返回
        return new PageBean(p.getTotal(), p.getResult());
    }
    @Override
    public void delete(Integer id) {
        employeeMapper.delete(id);
    }
    @Transactional(rollbackFor = Exception.class) //开启事务,rollbackFor指定处理所有异常，默认情况下只能处理运行时异常
    @Override
    public void add(Employee employee) throws Exception {
        try {
            employeeMapper.add(employee);
            Integer id = employee.getId();
            // 新增的时候需要先获取 emp 表中的 id，所以需要先更新 emp 然后再添加 EmpExp，因此需要引入事务的概念
            List<EmpExpr> list = employee.getExprList();
            System.out.println("**********************************003irbnf13quyfrbu34iybf4uevy********************************");
            System.out.println(id);
            System.out.println("003irbnf13quyfrbu34iybf4uevy");
            list.forEach(empExpr -> {
                empExpr.setId(null);
                empExpr.setEmpId(id);
            });
            // 非runtimeexception 需要在 @Transactional 中设置 rollbackFor
//        if (true) {
////             测试事务异常不可提交，但独立新事务可以提交
//            throw new Exception("测试事务处理");
//        }
            empExprMapper.insertBatch(list);
        } finally {
            // empLogService.insertEmpLog 标记了事务，而且是一个新事务，和当前独立，所以可以在 try 失败的时候仍旧更新日志表；在新增员工信息时，无论是成功还是失败，都要记录操作日志。所以设置事务调用的时候,      // 无论新增员工成功与否，都需要添加操作日志
            empLogService.insertEmpLog(new EmpLog(null, LocalDateTime.now(), employee.toString()));
        }
    }
    // 开启事务，在代码出现异常时更新数据库都会失败，都成功才是成功，通常失败的时候是运行时失败就会回滚，使用的是两次分别查询，join 连表查询通过一次数据库交互即可获取所有关联数据，减少网络往返次数（尤其在高延迟环境下优势明显）。数据库引擎（如MySQL、PostgreSQL）会自动优化JOIN的执行计划，利用索引、缓存等机制提升效率。保证原子性，所以我们进行优化使用连表查询
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Employee get(Integer id) {
        List<EmpExpr> empExprList = empExprMapper.list(id);
        Employee employee = employeeMapper.get(id);
        employee.setExprList(empExprList);
        // 使用外连接在此次中性能更优，只是需要使用 resultType 在 mapper 中映射返回规则
        return getJoin(id);
    }
    public Employee getJoin(Integer id) {
        Employee employee = employeeMapper.getJoin(id);
        return employee;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Employee employee) {
        try {
            employee.setUpdateTime(LocalDateTime.now());
            employeeMapper.update(employee);
            Integer id = employee.getId();
            // 删除对应经验表
            empExprMapper.deleteById(id);
            List<EmpExpr> list = employee.getExprList();
            list.forEach(empExpr -> {
                empExpr.setId(null);
                empExpr.setEmpId(id);
            });
            empExprMapper.insertBatch(list);
        } finally {
            empLogService.insertEmpLog(new EmpLog(null, LocalDateTime.now(), employee.toString()));
        }
    }
    @Override
    public List<Employee> getAll() {
        return employeeMapper.getAll();
    }
    @Override
    public EmpLoginInfo login(Employee employee) {
        EmpLoginInfo empLoginInfo = employeeMapper.getLogin(employee);
        if (empLoginInfo != null) {
            //完善登录逻辑，登录成功需要生成JWT令牌
            //自定义有效载荷
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empLoginInfo.getId());
            claims.put("username", empLoginInfo.getUsername());
            //调用JWT工具类，生成令牌, 使用 jwt 进行处理
            String jwt = JwtUtils.generateJwt(claims);
            //3.如果查到数据，就构造EmpLoginInfo对象并返回
            return new EmpLoginInfo(empLoginInfo.getId(), empLoginInfo.getUsername(), empLoginInfo.getName(), jwt);
        }
        return null;
    }
}

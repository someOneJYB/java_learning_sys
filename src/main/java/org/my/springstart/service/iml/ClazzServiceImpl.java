package org.my.springstart.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.my.springstart.entity.Clazz;
import org.my.springstart.entity.EmpQueryParam;
import org.my.springstart.entity.PageBean;
import org.my.springstart.mapper.ClazzMapper;
import org.my.springstart.mapper.StudentMapper;
import org.my.springstart.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageBean list(EmpQueryParam param) {

        //1.设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        //2.调用mapper的列表查询方法
        List<Clazz> empList = clazzMapper.list(param);
        // page 为 sql 添加拦截器增加 count(*) 和 limit 查询总数和分页
        Page p = (Page) empList;        //强转对象，Page继承了ArrayList

        //3.封装PageBean对象并返回
        return new PageBean(p.getTotal(), p.getResult());
    }
    @Override
    public void add(Clazz clazz) {
        clazzMapper.add(clazz);
    }
    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }
    @Override
    public void deleteById(Integer id) throws Exception {
        // 删除前班级有学生不可以删除
        Integer count = studentMapper.getClass(id);
        if (count > 0) {
            throw new Exception("该班级下有学生无法删除");
        } else {
            clazzMapper.deleteById(id);
        }
    }
    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}

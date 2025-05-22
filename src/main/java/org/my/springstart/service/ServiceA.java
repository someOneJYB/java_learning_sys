package org.my.springstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
//    @Lazy       //解决循环依赖问题，ServiceA 和 ServiceB 会出现 bean 注入循环依赖，：在任意一方的@Autowired字段上添加@Lazy注解，如@Lazy @Autowired private ServiceB serviceB
//工作原理：延迟依赖注入，待主类初始化完成后再注入依赖对象，打破初始化死锁
    // 最佳实践：只需在循环依赖的任意一方添加@Lazy即可解决问题
//本质原因：通过延迟加载将初始化过程从串行改为并行
//开发建议：即使解决方案有效，也应尽量避免设计出循环依赖的架构
    // lazy等会注入 ServiceB serviceB，调用的时候才去加载
    @Autowired
    private ServiceB serviceB;

    public void add(){
        serviceB.getById();
    }
}

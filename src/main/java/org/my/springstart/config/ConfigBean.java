package org.my.springstart.config;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.io.SAXReader;
import org.my.springstart.service.DeptService;
import org.my.springstart.service.ServiceA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Lazy
@Configuration
public class ConfigBean {
    // @Autowired
    // private ServiceA serviceA;

    @Bean("SAXReader1")  //作用：程序启动时，会执行该方法，并将方法的返回值对象交由IOC容器管理
    //bean的名字默认是方法名, 可以通过name|value属性设置bean的名字
    //如果需要依赖注入其他bean对象，直接在形参列表声明即可
    public SAXReader saxReader(ServiceA serviceA){
        log.info("创建saxReader对象...................");
        serviceA.add();
        return new SAXReader();
    }
}

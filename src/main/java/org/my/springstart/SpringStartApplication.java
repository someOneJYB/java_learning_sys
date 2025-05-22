package org.my.springstart;

import another.HeaderParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.dom4j.io.SAXReader;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// 启动类识别不在一个 another 包下的类，注入到启动类容器里面
//@ComponentScan({"another", "org.my.springstart"})
@Import(HeaderParser.class)
//@ServletComponentScan       //开启Servlet组件扫描, 使用过滤器
public class SpringStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStartApplication.class, args);
    }
    //声明第三方bean
    @Bean //将当前方法的返回值对象交给IOC容器管理, 成为IOC容器bean
    public SAXReader saxReader(){
        return new SAXReader();
    }
}

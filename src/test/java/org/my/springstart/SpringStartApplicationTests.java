package org.my.springstart;

import com.google.gson.Gson;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.dom4j.Document;
import org.my.springstart.controller.DeptController;
import org.my.springstart.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringStartApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private ApplicationContext context;

    //获取bean对象
    @Test
    public void testGetBean(){
        //根据bean的名称获取， 默认是类名
        DeptController bean1 = (DeptController) context.getBean("deptController");
        System.out.println("bean1 = " + bean1);

        //根据bean的名称获取
        System.out.println("bean2 = " + context.getBean("SAXReader1"));
        //根据bean的类型获取
        DeptController bean2 = context.getBean(DeptController.class);
        System.out.println("bean2 = " + bean2);

        //根据bean的名称 及 类型获取
        DeptController bean3 = context.getBean("deptController", DeptController.class);
        System.out.println("bean3 = " + bean3);
    }


    //bean的作用域，默认使用的是单例模式，打印输出的实例和地址都是相同的
    @Test
    public void testScope(){

        for (int i = 0; i < 10; i++) {
            DeptController bean = context.getBean(DeptController.class);
            System.out.println("bean = " + bean);
        }

    }

    @Autowired
    private SAXReader saxReader;

    //第三方bean的管理，使用 configuration 配置来设置自动注入第三方 bean
    @Test
    public void testThirdBean() throws Exception {

        // SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        System.out.println(name + " : " + age);
    }

    //测试bean的名字
    @Test
    public void testBeanName(){
        Object bean1 = context.getBean("SAXReader");
        System.out.println("bean1 = "+bean1);
        Object bean2 = context.getBean("saxReader");
        System.out.println("bean2 = "+bean2);
    }

    @Autowired
    private Gson gson;

    @Test
    public void testGson(){
        String json = gson.toJson(Result.success());
        System.out.println(json);
    }
}

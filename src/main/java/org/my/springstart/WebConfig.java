package org.my.springstart;

import org.my.springstart.interceptor.DemoInterceptor;
import org.my.springstart.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器识别配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired private LoginCheckInterceptor demoInterceptor;
    @Override public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}

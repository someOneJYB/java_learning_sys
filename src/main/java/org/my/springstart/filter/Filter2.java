package org.my.springstart.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class Filter2 implements Filter {
    //每次拦截到了请求，就会触发该方法，会调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Demo2Filter....doFilter....放行前逻辑");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("Demo2Filter....doFilter....放行后逻辑");
    }
}

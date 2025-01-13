package com.itheima.reggie.config;

import com.itheima.reggie.interceptors.LoginInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author aql
 * @Date 2025/1/13 15:53
 * @Version 1.0
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptors loginInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录和注册接口不拦截
        registry.addInterceptor(loginInterceptors).excludePathPatterns("/login", "/logout","/register");
    }
}

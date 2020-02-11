//package com.how2java.springboot.config;
//
//import com.how2java.springboot.utils.interceptor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//// 拦截器配置类
//@Component
//public class interceptorConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        /**
//         * 这里的addPathPatterns("/**")为配置需要拦截的方法“/**”代表所有，而后excludePathPatterns("/enterAdminLogin")等方法为排除哪些方法不进行拦截
//         */
//        registry.addInterceptor(new interceptor()).addPathPatterns("/**").excludePathPatterns("/enterAdminLogin").excludePathPatterns
//                ("/enterUserLogin").excludePathPatterns("/enterUserRegister").excludePathPatterns("static/**");
//        super.addInterceptors(registry);
//    }
//}
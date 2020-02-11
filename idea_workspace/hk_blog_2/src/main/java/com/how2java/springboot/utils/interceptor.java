package com.how2java.springboot.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器
public class interceptor implements HandlerInterceptor {
    /**
     * 拦截（Controller方法调用之前）
     *
     * @param request request
     * @param response response
     * @param o    o
     * @return 通过与否
     * @throws Exception 异常处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
            o) throws Exception {
        if (request.getSession().getAttribute("state") == null) {
            // 拦截至登陆页面
            request.getRequestDispatcher("/enterUserLogin").forward(request, response);
            System.out.println("访问了不该访问的页面，被拦截，进入登陆界面");
            // false为不通过
            return false;
        }
        System.out.println("可以访问，放行");
        // true为通过
        return true;
    }

    // 此方法为处理请求之后调用（调用过controller方法之后，跳转视图之前）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    // 此方法为整个请求结束之后进行调用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
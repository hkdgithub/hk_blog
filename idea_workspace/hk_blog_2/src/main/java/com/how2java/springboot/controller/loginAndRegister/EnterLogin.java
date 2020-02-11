package com.how2java.springboot.controller.loginAndRegister;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//访问登陆页面和管理员页面
@Controller

public class EnterLogin {

    //访问普通用户登陆页面
    @RequestMapping("/enterUserLogin")
    public String enterUserLogin(HttpServletRequest request ,HttpSession session , String exit){
        session.setAttribute("state" ,"用户登陆");
        if(exit == null){
            request.removeAttribute("state");
        }
        return "userLogin";
    }

    //访问普通用户注册界面
    @RequestMapping("/enterUserRegister")
    public String enterRegister(HttpSession session){
        session.setAttribute("state","用户注册");
        return "userRegister";
    }

    //访问管理员页面
    @RequestMapping("/enterAdminLogin")
    public String enterAdminLogin(HttpSession session ,HttpServletRequest request ,String exit){
        session.setAttribute("state","管理员登陆");
        if(exit == null){
            request.removeAttribute("state");
        }
        return "adminLogin";
    }

    //测试拦截器
    @RequestMapping("/test")
    public String test(HttpSession session){
        return "adminLogin";
    }

}

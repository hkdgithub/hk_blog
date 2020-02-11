package com.how2java.springboot.controller.loginAndRegister;

import com.how2java.springboot.service.blogUserService;
import com.how2java.springboot.service.logManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.how2java.springboot.utils.md5;

import java.text.SimpleDateFormat;
import java.util.Date;


//用户注册
@Controller
public class register {

    //博客用户服务
    @Autowired
    private blogUserService bus;
    //日志服务
    @Autowired
    private logManageService lms;

    @RequestMapping("/UserRegister")
    public String UserRegister(Model m ,String logname ,String logpass){
        //获得时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String time = format.format(date);

        System.out.println("用户"+logname+"开始注册");
        //将密码进行加密
        //判断是否出存在该用户
        if(bus.queryblogUser(logname ,md5.inputPassToFormPass(logpass)) == null){
            //没有该用户，开始注册
            bus.addblogUser(logname ,md5.inputPassToFormPass(logpass));
            m.addAttribute("message" ,"注册成功，请返回登录界面");
            System.out.println("用户"+logname+"注册完成");
            lms.addLog("用户"+logname+"注册完成" ,time);

        }
        else{
            m.addAttribute("message" ,"存在该用户，请重新输入");
            System.out.println("用户"+logname+"注册失败");
            lms.addLog("用户"+logname+"尝试登录,但已经存在该用户",time);
        }

        return "userRegister";
    }
}

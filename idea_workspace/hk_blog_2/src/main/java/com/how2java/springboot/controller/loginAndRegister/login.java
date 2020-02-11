package com.how2java.springboot.controller.loginAndRegister;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.domain.blog_info;
import com.how2java.springboot.domain.blog_tags;
import com.how2java.springboot.service.*;
import com.how2java.springboot.utils.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//普通用户登陆和管理员用户登陆
@Controller
public class login {

    //博客用户
    @Autowired
    private blogUserService bus;
    //管理员
    @Autowired
    private adminUserService aus;
    //博客信息
    @Autowired
    private blogManageService bms;
    //博客标签
    @Autowired
    private blogTagsService bts;
    //日志信息
    @Autowired
    private logManageService lms;

    //获得时间
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String time = format.format(date);

    //普通用户登陆
    @RequestMapping("/UserLogin")
    public String userLogin(Model m , HttpSession session, String logname , String logpass , @RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="9") Integer size){

        System.out.println("用户"+logname+"正在登录");

        //将密码进行加密
        //判断是否出存在该用户
        if(bus.queryblogUser(logname ,md5.inputPassToFormPass(logpass)) == null){
            //没有该用户
            m.addAttribute("message" ,"用户名或密码错误");
            lms.addLog("用户"+logname+"尝试登陆,但是用户名或密码错误" ,time);
            return "userLogin";

        }

        System.out.println("用户"+logname+"登陆完成，进入博客界面");
            lms.addLog("用户"+logname+"登陆成功" ,time);

        int i = 0;
        List<blog_info> list1 = new ArrayList<blog_info>();
        //list：博客中的全部信息
        List<blog_info> list = bms.queryAllBlog();
        //list2:博客按阅读人数进行降序排列，前5个
        List<blog_info> list2 = bms.queryDescReadNumber();
        //list3: 全部标签信息
        List<blog_tags> list3 = bts.queryAllTags();

        //list1：博客中的推荐信息
        for(blog_info bi : list){

            list1.add(bi);
            i++;
            if(i >= 5){
                break;
            }
        }

        PageHelper.startPage(start ,size);
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        m.addAttribute("list1" ,list1);
        m.addAttribute("list2" ,list2);
        m.addAttribute("list3" ,list3);

        session.setAttribute("state" ,"用户登陆");
        return "blogBefore/index";


    }

    //管理员用户登陆
    @RequestMapping("/AdminLogin")
    public  String adminLogin(Model m ,HttpSession session ,String logname ,String logpass ,@RequestParam(value="start",
            defaultValue="0") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){

        System.out.println("管理员"+logname+"开始登陆");

        //判断是否出存在该用户
        if(aus.queryAdminUser(logname ,logpass) == null){
            //没有该用户
            m.addAttribute("message" ,"用户名或密码错误");
            System.out.println("管理员"+logname+"登录失败");
            lms.addLog("管理员"+logname+"尝试登陆，但是用户名或密码错误" ,time);
            return "adminLogin";
        }
        m.addAttribute("currentUser: " ,"当前管理员"+logname);
        System.out.println("管理员"+logname+"登陆成功，进入管理员页面");
        lms.addLog("管理员"+logname+"登陆成功" ,time);
        PageHelper.startPage(start ,size);
        List<blog_info> list = bms.queryAllBlog();
        PageInfo<blog_info> page = new PageInfo<>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);

        session.setAttribute("state" ,"管理员登陆");
        return "blogLater/adminMain";

    }
}

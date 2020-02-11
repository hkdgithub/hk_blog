package com.how2java.springboot.controller.blogBefore;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.domain.blog_comment_content;
import com.how2java.springboot.domain.blog_info;
import com.how2java.springboot.domain.comment;
import com.how2java.springboot.service.blogCommentContentService;
import com.how2java.springboot.service.blogManageService;
import com.how2java.springboot.service.logManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class blogCommentManage {

    //博客评论管理
    @Autowired
    private blogCommentContentService bccs;
    //博客服务
    @Autowired
    private blogManageService bms;
    //日志管理
    @Autowired
    private logManageService lms;

    //获得时间
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String time = format.format(date);

    @RequestMapping("/addBlogComment")
    public String addBlogComment(Model m ,String blogTitle ,String name ,String sex ,String email ,String content){

        //插入评论
        bccs.addBlogComment(blogTitle ,name ,sex ,email ,content ,time);

        blog_info bi = bms.queryBlogForTitle(blogTitle);
        m.addAttribute("c" ,bi);

        List<blog_comment_content> list = bccs.queryBlogCommentForTitle(blogTitle);
        m.addAttribute("d" ,list);
        lms.addLog(name+"评论"+blogTitle+"完成" ,time);

        return "blogBefore/article_detail";
    }

    //删除博客评论
    @RequestMapping("/DeleteBlogComment")
    public String DeleteBlogComment(Model m ,String blogCommentTime ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){
        bccs.deleteBlogComment(blogCommentTime);
        PageHelper.startPage(start ,size);
        List<blog_comment_content> list = bccs.queryAllBlogComment();
        PageInfo<blog_comment_content> page = new PageInfo<blog_comment_content>();
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        lms.addLog("删除时间为"+blogCommentTime+"的博客" ,time);
        return "blogLater/commentManage";
    }
}

package com.how2java.springboot.controller.pageJump;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.domain.blog_comment_content;
import com.how2java.springboot.domain.blog_info;
import com.how2java.springboot.domain.blog_tags;
import com.how2java.springboot.domain.comment;
import com.how2java.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//博客前台跳转
@Controller
public class blogBefore {

    //博客管理
    @Autowired
    private blogManageService bms;
    //标签管理
    @Autowired
    private blogTagsService bts;
    //博客评论管理
    @Autowired
    private blogCommentContentService bccs;
    //日志管理
    @Autowired
    private logManageService lms;
    //评论
    @Autowired
    private commentService cs;

    //获得时间
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String time = format.format(date);

    //跳转到博客首页
    @RequestMapping("/enterIndex")
    public String enterIndex(Model m , @RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="9") Integer size){

        //TODO 判断项目是否发布


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


        return "blogBefore/index";
    }

    //跳转到关于我
    @RequestMapping("/enterAbout")
    public String enterAbout(){
        return "blogBefore/about";
    }

    //跳转到开发历程
    @RequestMapping("/enterMood")
    public String enterMood(){
        return "blogBefore/mood";
    }

    //跳转到留言版
    @RequestMapping("/enterBoard")
    public String enterBoard(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="9") Integer size){
        PageHelper.startPage(start ,size);
        List<comment> list = cs.queryAllComment();
        PageInfo<comment> page = new PageInfo<comment>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        return "blogBefore/board";
    }

    //跳转到生活分享
    @RequestMapping("/enterliveSharp")
    public String enterliveSharp(){return "blogBefore/liveSharp";}

    //跳转到博客具体内容
    @RequestMapping("/enterArticleDetail")
    public String enterArticleDetail(Model m ,String blogTitle){

        //博客浏览量加一
        bms.updateBlogReadNumber(blogTitle);
        //查询该条博客
        blog_info bi = bms.queryBlogForTitle(blogTitle);
        m.addAttribute("c" ,bi);
        //查询所以的评论
        List<blog_comment_content> list = bccs.queryBlogCommentForTitle(blogTitle);
        m.addAttribute("d" ,list);


        return "blogBefore/article_detail";
            }
}

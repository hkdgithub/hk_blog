package com.how2java.springboot.controller.pageJump;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.config.ExportExcelUtils;
import com.how2java.springboot.domain.*;
import com.how2java.springboot.service.*;
import com.how2java.springboot.utils.sendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

//博客后台跳转
@Controller
public class blogLater {

    //博客管理
    @Autowired
    private blogManageService bms;
    //标签管理
    @Autowired
    private blogTagsService bts;
    //分类管理
    @Autowired
    private blogCategoryService bcs;
    //评论
    @Autowired
    private commentService cs;
    //日志管理
    @Autowired
    private logManageService lms;
    //评论管理
    @Autowired
    private blogCommentContentService bccs;
    //发送邮件
    @Autowired
    private sendEmail se;

    //获得时间
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String time = format.format(date);

    //跳转到博客管理
    @RequestMapping("/enterBlogManage")
    public String enterBlogManage(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){

        List<blog_info> list = bms.queryAllBlog();
        PageHelper.startPage(start ,size);
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);

        return "blogLater/adminMain";
    }

    //跳转到撰写博客
    @RequestMapping("/enterWriteBlog")
    public String enterWriteBlog(Model m){
        return "blogLater/writeBlog";
    }

    //跳转到评论管理
    @RequestMapping("/enterCommentManage")
    public String enterCommentManage(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="8") Integer size){

        List<blog_comment_content> list = bccs.queryAllBlogComment();
        PageHelper.startPage(start ,size);
        PageInfo<blog_comment_content> page = new PageInfo<blog_comment_content>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        return "blogLater/commentManage";
    }

    //跳转到分类管理
    @RequestMapping("/enterCategoryManage")
    public String enterCategoryManage(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="8") Integer size){
        List<blog_category> list = bcs.queryAllCategory();
        PageHelper.startPage(start ,size);
        PageInfo<blog_category> page = new PageInfo<blog_category>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);

        return "blogLater/categoryManage";
    }

    //跳转到标签管理
    @RequestMapping("/enterTagsManage")
    public String enterTagsManage(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="8") Integer size){
        List<blog_tags> list = bts.queryAllTags();
        PageHelper.startPage(start ,size);
        PageInfo<blog_tags> page = new PageInfo<blog_tags>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);

        return "blogLater/tagsManage";
    }


    //跳转到博客修改界面
    @RequestMapping("/enterUpdateBlog")
    public String enterUpdateBlog(Model m){
        return "blogLater/updateBlog";
    }

    //跳转到评论
    @RequestMapping("/enterComment")
    public String enterComment(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="8") Integer size){
        List<comment> list = cs.queryAllComment();
        PageHelper.startPage(start ,size);
        PageInfo<comment> page = new PageInfo<>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list" ,list);

        return "blogLater/comment";
    }

    //跳转到日志管理
    @RequestMapping("/enterLogManage")
    public String enterLogManage(Model m,@RequestParam(value="start",
            defaultValue="1") Integer start ,@RequestParam(value="size",defaultValue="18") Integer size){
        List<log> list = lms.queryAllLog();
        PageHelper.startPage(start ,size);
        PageInfo<log> page = new PageInfo<log>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list" ,list);
        return "blogLater/logManage";
    }

    //导出博客信息
    @RequestMapping("/exportBlogInfo")
    public void exportBlogInfo(HttpServletResponse response, HttpServletRequest request){

        List<blog_info> list =bms.queryAllBlog();
        String excelName = "博客信息表";
        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("blogId","博客编号");
        fieldMap.put("blogTitle","标题");
        fieldMap.put("blogCoverImage","预览图");
        fieldMap.put("blogContent","内容");
        fieldMap.put("blogCategoryId","分类");
        fieldMap.put("blogTagsId","标签");
        fieldMap.put("blogState","状态");
        fieldMap.put("createTime","创建时间");
        fieldMap.put("issueTime","发布时间");
        //导出用户相关信息
        new ExportExcelUtils().export(excelName,list,fieldMap,response);

        lms.addLog("导出博客信息" ,time);

    }

    //导出分类信息
    @RequestMapping("/exportCategoryInfo")
    public void exportCategoryInfo(HttpServletResponse response, HttpServletRequest request){
        List<blog_category> list = bcs.queryAllCategory();
        String excelName = "博客分类表";
        LinkedHashMap<String ,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("categoryId","编号");
        fieldMap.put("name" ,"名称");
        fieldMap.put("rank" ,"使用次数");
        fieldMap.put("createTime" ,"创建时间");
        //导出相关信息
        new ExportExcelUtils().export(excelName ,list ,fieldMap ,response);

        lms.addLog("导出分类信息" ,time);
    }

    //导出标签信息
    @RequestMapping("/exportTagsInfo")
    public void exportTagsInfo(HttpServletResponse response, HttpServletRequest request){
        List<blog_tags> list =bts.queryAllTags();
        String excelName = "博客标签表";
        LinkedHashMap<String ,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("tagId","编号");
        fieldMap.put("name" ,"名称");
        fieldMap.put("rank" ,"使用次数");
        fieldMap.put("createTime" ,"创建时间");
        //导出相关信息
        new ExportExcelUtils().export(excelName ,list ,fieldMap ,response);

        lms.addLog("导出标签信息" ,time);
    }

    //导出评论
    @RequestMapping("/exportComment")
    public void exportComment(HttpServletResponse response ,HttpServletRequest request){
        List<comment> list = cs.queryAllComment();
        String excelName = "评论表";
        LinkedHashMap<String ,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("Id","编号");
        fieldMap.put("content","评论内容");
        fieldMap.put("commentTime","评论时间");
        fieldMap.put("commentPeoper","评论人");
        fieldMap.put("commentLikeNumber","评论喜欢的人数");
        fieldMap.put("replyPeoper","回复人");
        fieldMap.put("replyComment","回复内容");
        fieldMap.put("replyLikeNumber","回复喜欢的人数");
        fieldMap.put("replyTime","回复时间");
        fieldMap.put("replyState","回复状态");
        //导出相关信息
        new ExportExcelUtils().export(excelName ,list ,fieldMap ,response);

        lms.addLog("导出评论" ,time);
    }

    //导出日志
    @RequestMapping("/exportLog")
    public void exportLog(HttpServletResponse response ,HttpServletRequest request){
        List<log> list = lms.queryAllLog();
        String excelName = "日志表";
        LinkedHashMap<String ,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("Id" ,"编号");
        fieldMap.put("operation" ,"操作");
        fieldMap.put("operation" ,"操作时间");
        //导出相关信息
        new ExportExcelUtils().export(excelName ,list ,fieldMap ,response);
        lms.addLog("导出日志" ,time);
    }

    //导出评论管理
    @RequestMapping("/exportCommentManage")
    public void exportCommentManage(HttpServletResponse response ,HttpServletRequest request){
        List<blog_comment_content> list = bccs.queryAllBlogComment();
        String excelName = "博客评论表";
        LinkedHashMap<String ,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("blogTitle" ,"博客标题");
        fieldMap.put("blogCommentPeoperName" ,"博客评论人昵称");
        fieldMap.put("blogCommentPeoperSex" ,"博客评论人性别");
        fieldMap.put("blogCommentPeoperEmail" ,"博客评论人电子邮箱");
        fieldMap.put("blogCommentContent" ,"博客评论人内容");
        fieldMap.put("blogCommentTime" ,"博客评论时间");
        //导出相关信息
        new ExportExcelUtils().export(excelName ,list ,fieldMap ,response);
        lms.addLog("导出日志" ,time);
    }

    //发送邮件
    @RequestMapping("/sendEmail")
    public String  sendEmail(Model m ,String subject ,String content ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){
        List<blog_comment_content> list = bccs.queryAllBlogComment();
        try{
            for (blog_comment_content a : list){
            se.sendHtmlMail(a.getBlogCommentPeoperEmail() ,subject ,content);
        }
        lms.addLog("给所有的博客评论者发送邮件"+subject+"成功" ,time);
            m.addAttribute("state" ,"发送邮件成功");
        }catch (Exception e){
            lms.addLog("给所有的博客评论者发送邮件"+subject+"失败" ,time);
            m.addAttribute("state" ,"发送邮件失败");
        }
        PageHelper.startPage(start ,size);
        PageInfo<blog_comment_content> page = new PageInfo<blog_comment_content>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list" ,list);

        return "blogLater/commentManage";
    }
}

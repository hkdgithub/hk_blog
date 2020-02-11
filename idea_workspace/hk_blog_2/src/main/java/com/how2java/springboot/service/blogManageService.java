package com.how2java.springboot.service;

import com.how2java.springboot.dao.blogManageMapper;
import com.how2java.springboot.domain.blog_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogManageService {

    @Autowired
    private blogManageMapper bmm;

    //博客添加
    public Boolean addBlogInfo(String blogTitle ,String blogCoverImage ,String blogContent ,String blogCategoryId
            ,String blogTagsId ,String blogState ,String createTime ,String issueTime){
        bmm.add(blogTitle ,blogCoverImage ,blogContent ,blogCategoryId ,blogTagsId ,blogState ,createTime ,issueTime);
        return true;
    }

    //所有博客查询
    public List<blog_info> queryAllBlog(){
     List<blog_info> list = bmm.queryAllBlog();
     return list;
    }

    //单个博客
    public blog_info querySingleBlog(String blogId){
        blog_info bi = bmm.querySingleBlog(blogId);
        return bi;
    }

    //单个博客（根据blogTitle）
    public blog_info queryBlogForTitle(String blogTitle){
        blog_info bi = bmm.queryBlogForTitle(blogTitle);
        return bi;
    }

    //按降排序查询博客阅读数量前5个信息
    public List<blog_info> queryDescReadNumber(){
        List<blog_info> list = bmm.queryDescReadNumber();
        return list;
    }

    //更改博客状态
    public Boolean updateBlogState(String blogId ,String blogState ,String issueTime){
        bmm.updateBlogState(blogId ,blogState ,issueTime);
        return true;
    }

    //更改博客浏览量
    public Boolean updateBlogReadNumber(String blogTitle){
        bmm.updateBlogReadNumber(blogTitle);
        return true;
    }

    //删除博客
    public Boolean deleteBlog(int blogId){
        bmm.deleteBlog(blogId);
        return true;
    }

    //修改博客
    public Boolean updateBlog(String blogTitle ,String blogContent){
        bmm.updateBlog(blogTitle ,blogContent);
        return true;
    }

}

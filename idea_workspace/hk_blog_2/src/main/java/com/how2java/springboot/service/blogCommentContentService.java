package com.how2java.springboot.service;

import com.how2java.springboot.dao.blogCommentContentMapper;
import com.how2java.springboot.domain.blog_comment_content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogCommentContentService {

    @Autowired
    private blogCommentContentMapper  bccm;

    //查询所有的博客评论
    public List<blog_comment_content> queryAllBlogComment(){
        List<blog_comment_content> list = bccm.queryAllBlogComment();
        return list;
    }

    //添加博客评论
    public boolean addBlogComment(String blogTitle ,String blogCommentPeoperName ,String blogCommentPeoperSex ,
                                  String blogCommentPeoperEmail ,String blogCommentContent ,String blogCommentTime){
        bccm.addBlogComment(blogTitle ,blogCommentPeoperName ,blogCommentPeoperSex ,blogCommentPeoperEmail ,blogCommentContent ,blogCommentTime);
        return true;
    }

    //查询所有的博客评论（根据blogTitle）
    public List<blog_comment_content> queryBlogCommentForTitle(String blogTitle){
       List<blog_comment_content>  list =  bccm.queryBlogCommentForTitle(blogTitle);
        return list;
    }

    //删除博客评论
    public boolean deleteBlogComment(String blogCommentTime){
        bccm.deleteBlogComment(blogCommentTime);
        return true;
    }
}

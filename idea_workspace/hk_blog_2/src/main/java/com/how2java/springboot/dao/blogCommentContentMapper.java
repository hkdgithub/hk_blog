package com.how2java.springboot.dao;

import com.how2java.springboot.domain.blog_comment_content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface blogCommentContentMapper {

    //查询所有的博客评论
    List<blog_comment_content> queryAllBlogComment();

    //添加博客评论
    int addBlogComment(@Param("blogTitle") String blogTitle ,@Param("blogCommentPeoperName") String blogCommentPeoperName ,@Param("blogCommentPeoperSex") String blogCommentPeoperSex ,
                      @Param("blogCommentPeoperEmail") String blogCommentPeoperEmail ,@Param("blogCommentContent") String blogCommentContent ,@Param("blogCommentTime") String blogCommentTime);

    //查询所有的博客评论（根据blogTitle）
    List<blog_comment_content> queryBlogCommentForTitle(@Param("blogTitle")String blogTitle);

    //删除博客评论
    int deleteBlogComment(@Param("blogCommentTime")String blogCommentTime);
}

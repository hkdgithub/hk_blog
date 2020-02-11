package com.how2java.springboot.dao;

import com.how2java.springboot.domain.blog_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface blogManageMapper {

    //插入博客信息
    int add(@Param("blogTitle") String blogTitle, @Param("blogCoverImage") String blogCoverImage ,@Param("blogContent") String blogContent
            , @Param("blogCategoryId") String blogCategoryId, @Param("blogTagsId") String blogTagsId
            , @Param("blogState") String blogState, @Param("createTime") String createTime ,@Param("issueTime") String issueTime);

    //查询所有的博客
    List<blog_info> queryAllBlog();

    //查询单个博客信息
    blog_info querySingleBlog(@Param("blogId") String blogId);

    //查询单个博客信息（根据blogTitle）
    blog_info queryBlogForTitle(@Param("blogTitle")String blogTitle);

    //按降排序查询博客阅读数量前5个信息
    List<blog_info> queryDescReadNumber();

    //更改博客状态
    int updateBlogState(@Param("blogId") String blogId ,@Param("blogState") String blogState ,@Param("issueTime") String issueTime);

    //更改博客浏览量
    int updateBlogReadNumber(@Param("blogTitle") String blogTitle);

    //删除博客
    int deleteBlog(@Param("blogId") int blogId);

    //修改博客
    int updateBlog(@Param("blogTitle") String blogTitle , @Param("blogContent") String blogContent);

}

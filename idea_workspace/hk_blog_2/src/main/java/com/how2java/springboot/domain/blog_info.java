package com.how2java.springboot.domain;

import lombok.Data;

//博客信息
@Data
public class blog_info {

    //博客id
    public int blogId;
    //博客标题
    public String blogTitle;
    //博客封面图片
    public String blogCoverImage;
    //博客内容
    public String blogContent;
    //博客分类id
    public String blogCategoryId;
    //博客标签id
    public String blogTagsId;
    //博客状态
    public String blogState;
    //博客阅读数量
    public int blogReadNumber;
    //博客创建时间
    public String createTime;
    //博客发布时间
    public String issueTime;
}

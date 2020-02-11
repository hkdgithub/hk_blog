package com.how2java.springboot.domain;

import lombok.Data;

@Data
public class blog_comment_content {

    //博客标题
    String blogTitle;
    //博客评论人姓名
    String blogCommentPeoperName;
    //博客评论人性别
    String blogCommentPeoperSex;
    //博客评论人邮箱
    String blogCommentPeoperEmail;
    //博客评论内容
    String blogCommentContent;
    //博客评论时间
    String blogCommentTime;

}

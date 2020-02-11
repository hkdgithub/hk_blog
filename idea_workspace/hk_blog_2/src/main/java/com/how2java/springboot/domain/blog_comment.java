package com.how2java.springboot.domain;

import lombok.Data;

//博客评论
@Data
public class blog_comment {

    //博客id
    public int blogId;
    //评论内容
    public String commentContent;
    //评论时间
    public String commentTime;
    //评论人名称
    public String commentName;
    //评论人邮箱
    public String commentEmail;
    //状态
    public String state;
    //回复内容
    public String replyContent;
}

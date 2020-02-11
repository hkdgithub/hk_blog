package com.how2java.springboot.domain;

import lombok.Data;

//评论表
@Data
public class comment {
    //编号
    int Id;
    //评论内容
    String content;
    //评论时间
    String commentTime;
    //评论人
    String commentPeoper;
    //回复人
    String replyPeoper;
    //回复内容
    String replyComment;
    //回复时间
    String replyTime;
    //回复状态
    String replyState;
}

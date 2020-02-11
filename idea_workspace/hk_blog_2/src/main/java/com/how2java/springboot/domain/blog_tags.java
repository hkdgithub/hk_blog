package com.how2java.springboot.domain;

import lombok.Data;

//博客标签
@Data
public class blog_tags {

    //标签id
    public int tagId;
    //标签name
    public String name;
    //创建时间
    public String createTime;
    //使用次数
    public int rank;

}

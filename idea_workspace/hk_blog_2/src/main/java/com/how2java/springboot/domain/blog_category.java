package com.how2java.springboot.domain;

import lombok.Data;

//博客分类
@Data
public class blog_category {

    public int categoryId;
    //姓名
    public String name;
    //使用次数
    public int rank;
    //创建时间
    public String createTime;

}

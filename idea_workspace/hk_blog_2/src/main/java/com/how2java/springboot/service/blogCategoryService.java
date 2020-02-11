package com.how2java.springboot.service;

import com.how2java.springboot.dao.categoryManageMapper;
import com.how2java.springboot.dao.tagsManageMapper;
import com.how2java.springboot.domain.blog_category;
import com.how2java.springboot.domain.blog_tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogCategoryService {

    @Autowired
    private categoryManageMapper cmm;

    //标签查询
    public List<blog_category> queryAllCategory(){
        List<blog_category> list = cmm.queryAllCategory();
        return list;
    }

    //添加标签
    public boolean addCategory(String name ,String createTime ,int rank){
        cmm.addCategory(name ,createTime ,rank);
        return true;
    }

    //修改标签
    public boolean updateCategoryRank(String name , int rank){
        cmm.updateCategoryRank(name ,rank);
        return true;
    }

    //删除标签
    public boolean deleteCategory(String name){
        cmm.deleteCategory(name);
        return true;
    }

}

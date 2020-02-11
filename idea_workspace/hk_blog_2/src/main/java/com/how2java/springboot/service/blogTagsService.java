package com.how2java.springboot.service;

import com.how2java.springboot.dao.tagsManageMapper;
import com.how2java.springboot.domain.blog_tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogTagsService {

    @Autowired
    private tagsManageMapper tmm;

    //标签查询
    public List<blog_tags> queryAllTags(){
        List<blog_tags> list = tmm.queryAllTags();
        return list;
    }

    //添加标签
    public boolean addTags(String name ,String createTime ,int rank){
        tmm.addTags(name ,createTime ,rank);
        return true;
    }

    //修改标签
    public boolean updateTagsRank(String name ,int rank){
        tmm.updateTagsRank(name ,rank);
        return true;
    }

    //删除标签
    public boolean deleteTags(String name){
        tmm.deleteTags(name);
        return true;
    }

}

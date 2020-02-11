package com.how2java.springboot.dao;

import com.how2java.springboot.domain.blog_tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface tagsManageMapper {

    //查询标签
    List<blog_tags> queryAllTags();

    //添加标签
    int addTags(@Param("name") String name ,@Param("createTime") String createTime ,@Param("rank") int rank);

    //根据标签名称修改标签
    int updateTagsRank(@Param("name") String name ,@Param("rank") int rank);

    //删除标签
    int deleteTags(@Param("name") String name);
}

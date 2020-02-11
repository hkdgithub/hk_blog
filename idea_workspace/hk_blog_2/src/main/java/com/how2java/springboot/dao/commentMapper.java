package com.how2java.springboot.dao;

import com.how2java.springboot.domain.comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@Mapper
public interface commentMapper {

    //添加评论
    int addComment(@Param("content") String content ,@Param("commentTime") String commentTime ,
                   @Param("commentPeoper") String commentPeoper ,@Param("replyPeoper") String replyPeoper ,
                   @Param("replyComment") String replyComment ,@Param("replyTime") String replyTime ,
                   @Param("replyState") String replyState);

    //查询所有评论
    List<comment> queryAllComment();

    //删除评论
    int deleteComment(@Param("Id")int Id);

    //添加回复
    int addReply(@Param("Id") int Id ,@Param("replyPeoper")String replyPeoper ,@Param("replyComment")String replyComment ,
                 @Param("replyTime")String replyTime ,@Param("replyState")String replyState);

}

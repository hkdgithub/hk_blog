package com.how2java.springboot.service;

import com.how2java.springboot.dao.commentMapper;
import com.how2java.springboot.domain.comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentService {

    @Autowired
    private commentMapper cm;

    //添加评论
    public boolean addComment(String content ,String commentTime ,String commentPeoper ,
                              String replyPeoper ,String replyComment ,String replyTime ,String replyState){
        cm.addComment(content ,commentTime ,commentPeoper ,replyPeoper ,replyComment ,replyTime ,replyState);
        return true;
    }

    //查询所有的评论
    public List<comment> queryAllComment(){
      List<comment> list = cm.queryAllComment();
      return list;
    }

    //删除评论
    public boolean deleteComment(int Id){
        cm.deleteComment(Id);
        return true;
    }

    //回复评论
    public boolean addReply(int Id ,String replyPeoper ,String replyComment ,String replyTime ,String replyState){
        cm.addReply(Id ,replyPeoper ,replyComment ,replyTime ,replyState);
        return true;
    }
}

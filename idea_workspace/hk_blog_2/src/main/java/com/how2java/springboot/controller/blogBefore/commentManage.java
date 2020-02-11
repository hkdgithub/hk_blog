package com.how2java.springboot.controller.blogBefore;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.domain.blog_info;
import com.how2java.springboot.domain.comment;
import com.how2java.springboot.service.commentService;
import com.how2java.springboot.service.logManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin.PluginURLJarFileCallBack;

import javax.xml.ws.Action;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class commentManage {

    //评论服务
    @Autowired
    private commentService cs;
    //日志管理
    @Autowired
    private logManageService lms;

    //获得时间
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String time = format.format(date);

    //评论上传
    @RequestMapping("/SubmitComment")
    public String SubmitComment(Model m ,String name ,String comment ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="9") Integer size){

        //获得时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String commentTime = format.format(date);
        //回复状态
        String replyState = "尚未回复";

        cs.addComment(comment ,commentTime ,name ,null ,null ,null ,replyState);
        m.addAttribute("name" ,"评论上传成功");
        lms.addLog(name+"上传评论" ,time);

        PageHelper.startPage(start ,size);
        List<comment> list = cs.queryAllComment();
        PageInfo<comment> page = new PageInfo<comment>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);

        return "blogBefore/board";
    }

    //删除评论
    @RequestMapping("/DeleteComment")
    public String DeleteComment(Model m ,int Id ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){

        cs.deleteComment(Id);
        PageHelper.startPage(start ,size);
        List<comment> list = cs.queryAllComment();
        PageInfo<comment> page = new PageInfo<comment>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        return "blogLater/comment";
    }

    //评论回复
    @RequestMapping("/ReplyComment")
    public String ReplyComment(Model m , @Param("Id") String Id , String replyComment , @RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){

        System.out.println(Id+" "+replyComment);
        //回复状态
        String replyState = "已回复";
        //回复时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String replyTime = format.format(date);
        //回复人
        String replyPeoper = "站长";
        int Id1 = Integer.parseInt(Id);
        cs.addReply(Id1 ,replyPeoper ,replyComment ,replyTime ,replyState);
        PageHelper.startPage(start ,size);
        List<comment> list = cs.queryAllComment();
        PageInfo<comment> page = new PageInfo<comment>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        return "blogLater/comment";
    }

}

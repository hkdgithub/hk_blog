package com.how2java.springboot.controller.blogLater;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.domain.blog_category;
import com.how2java.springboot.domain.blog_info;
import com.how2java.springboot.domain.blog_tags;
import com.how2java.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class blogManage {

    //博客管理
    @Autowired
    private blogManageService bms;
    //标签管理
    @Autowired
    private blogTagsService bts;
    //分类管理
    @Autowired
    private blogCategoryService bcs;
    //日志
    @Autowired
    private logManageService lms;


    //获得时间
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String time = format.format(date);

    //写博客
    @RequestMapping("/WriteBlog")
    public String WriteBlog(Model m , String blogTitle , String blogCategoryId , String blogTagsId , MultipartFile file
            , String editor01){

        System.out.println("开始写博客"+blogTitle+" "+" "+blogCategoryId+" "+blogTagsId+" "+file+" "+editor01);
        if(file.isEmpty()){
            //返回文件提示
            return "请选择上传文件";
        }

        //保存图片的路径.
        String filePath = "C:\\Users\\lenovo\\Desktop\\idea_workspace\\hk_blog_2\\src\\main\\resources\\static\\img\\blogManage";
        //获取图片的扩展名
        String originaFilename = file.getOriginalFilename();
        System.out.println(originaFilename);

        //封装上传文件的全路径
        File targetFile = new File(filePath ,originaFilename);
        //把本地文件转存到封装上传文件位置的全路径
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //博客状态
        String blogState = "未发布";
        //发布时间
        String issueTime = "未发布";
        //获得时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = format.format(date);
        //浏览量
        //博客管理
        bms.addBlogInfo(blogTitle ,originaFilename,editor01 ,blogCategoryId ,blogTagsId ,blogState ,createTime ,issueTime);
        m.addAttribute("success" ,"写博客完成,请于博客管理中查看");
        lms.addLog("写博客"+blogTitle+"完成" ,time);
        System.out.println("写博客完成");

        //查询是否有该分类管理，没有的话新建分类
        //初始分类使用数为0
        int categoryRank = 1;
        int categoryFlag = 0;
        List<blog_category> list2 = bcs.queryAllCategory();
        for(blog_category bc:list2){

            //如果含有此属性，使用次数加1
            if(blogCategoryId.equals(bc.name)){
                //修改使用次数
                bcs.updateCategoryRank(bc.name ,bc.rank+1);
                categoryFlag = 1;
                lms.addLog("分类"+blogCategoryId+"使用次数+1" ,time);
                break;
            }
        }

        //如果不含此分类，新建
        if(categoryFlag == 0){
            bcs.addCategory(blogCategoryId ,createTime ,categoryRank);
            lms.addLog("新建分类"+blogCategoryId ,time);
        }

        //查询是否有该标签,没有的话新建标签
        //初始标签使用数为0
        int tagsRank = 1;
        int tagsFlag = 0;
        List<blog_tags> list1 = bts.queryAllTags();
        for(blog_tags bt:list1){

            //如果含有此标签，使用次数加1
            if(blogTagsId.equals(bt.name)){
                //修改使用次数
                bts.updateTagsRank(bt.name ,bt.rank+1);
               tagsFlag = 1;
                lms.addLog("标签"+blogTagsId+"使用次数+1" ,time);
               break;
            }
        }

        //如果不含此标签，新建
        if(tagsFlag == 0){
            bts.addTags(blogTagsId ,createTime ,tagsRank);
            lms.addLog("新建标签"+blogTagsId ,time);
        }

        return "blogLater/writeBlog";
    }

    //查博客
    @RequestMapping("/QueryBlog")
    public String QueryBlog(Model m ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){

        //登录完成后进入博客管理界面，需要分页
        PageHelper.startPage(start ,size);
        List<blog_info> list = bms.queryAllBlog();
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        return "blogLater/adminMain";
    }

    //删博客
    @RequestMapping("/DeleteBlog")
    public String Delete(Model m ,String blogId, String blogTagsId ,String blogCategoryId ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){
        System.out.println(blogId +" "+ blogCategoryId +" "+blogTagsId);

        int blogId1 = Integer.parseInt(blogId);
        if(bms.deleteBlog(blogId1)){
            System.out.println("删除博客"+blogId+"完成");
        }
        PageHelper.startPage(start ,size);
        List<blog_info> list = bms.queryAllBlog();
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        lms.addLog("删除博客"+blogId ,time);

        //根据blogCategoryId查询rank
        List<blog_category> list2 = bcs.queryAllCategory();

        for(blog_category bc : list2){

            if(bc.name.equals(blogCategoryId)){
                System.out.println(bc.name+" "+blogCategoryId);
                //分类使用次数减1
                bcs.updateCategoryRank(bc.name ,bc.rank-1);
                lms.addLog("分类"+blogCategoryId+"使用次数-1" ,time);
                //如果使用次数为0，删除分类
                if(bc.rank-1 == 0){
                    bcs.deleteCategory(bc.name);
                    lms.addLog("分类"+blogCategoryId+"使用次数为0，删除该分类" ,time);
                }

                break;
            }
        }

        //根据blogTagsId查询rank
        List<blog_tags> list1 = bts.queryAllTags();

        for(blog_tags bt : list1){

            if(bt.name.equals(blogTagsId)){
                //标签使用次数减1
                bts.updateTagsRank(bt.name ,bt.rank-1);
                lms.addLog("标签"+blogTagsId+"使用次数-1" ,time);
                //如果使用次数为0，删除标签
                if(bt.rank-1 == 0){
                    bts.deleteTags(bt.name);
                    lms.addLog("标签"+blogTagsId+"使用次数为0，删除该标签" ,time);
                }
                break;
            }
        }
        return "blogLater/adminMain";
    }

    //查询单个博客
    @RequestMapping("/QuerySingleBlog")
    public String QuerySingleBlog(Model m ,String blogId ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size) {
        PageHelper.startPage(start ,size);
        blog_info bi = bms.querySingleBlog(blogId);
        m.addAttribute("blogInfo" ,bi);
        List<blog_info> list = bms.queryAllBlog();
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        return "blogLater/adminMain";
    }

    //更改博客状态
    @RequestMapping("/UpdateBlogState")
    public String UpdateBlogState(Model m ,String blogId ,@RequestParam(value="start",
            defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){
        if("已发布".equals(blogId)){
            m.addAttribute("blogState","博客已发布");
            lms.addLog("博客已发布，但用户尝试重新发布" ,time);
        }
        else {
            //获得发布事间
            //获得时间
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String issueTime = format.format(date);
            bms.updateBlogState(blogId ,"已发布" ,issueTime);
            lms.addLog("博客"+blogId+"已发布"  ,time);
        }

        PageHelper.startPage(start ,size);
        List<blog_info> list = bms.queryAllBlog();
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);

        return "blogLater/adminMain";
    }

    //修改博客
    @RequestMapping("/UpdateBlog")
    public String BlogUpdate(Model m ,String blogTitle1 ,String blogContent1
    ,@RequestParam(value="start", defaultValue="1") Integer start, @RequestParam(value="size",defaultValue="3") Integer size){

        bms.updateBlog(blogTitle1 ,blogContent1);
        System.out.println( blogTitle1+" "+blogContent1);
        m.addAttribute("state"  ,"修改完成");

        PageHelper.startPage(start ,size);
        List<blog_info> list = bms.queryAllBlog();
        PageInfo<blog_info> page = new PageInfo<blog_info>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list",list);
        lms.addLog("修改博客"+blogTitle1+"完成" ,time);

        return "blogLater/adminMain";
    }


}

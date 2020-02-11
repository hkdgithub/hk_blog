package com.how2java.springboot.controller.blogLater;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.domain.log;
import com.how2java.springboot.service.logManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//日志管理
@Controller
public class logManage {

    @Autowired
    private logManageService lms;

    @RequestMapping("/deleteLog")
    public String deleteLog(Model m, @RequestParam(value="start",
            defaultValue="1") Integer start , @RequestParam(value="size",defaultValue="18") Integer size){
        lms.deleteAllLog();
        PageHelper.startPage(start ,size);
        List<log> list = lms.queryAllLog();
        PageInfo<log> page = new PageInfo<log>(list);
        m.addAttribute("page" ,page);
        m.addAttribute("list" ,list);
        return "blogLater/logManage";
    }
}

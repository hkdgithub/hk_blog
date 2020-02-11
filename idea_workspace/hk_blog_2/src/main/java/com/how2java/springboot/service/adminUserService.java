package com.how2java.springboot.service;

import com.how2java.springboot.dao.adminUserMapper;
import com.how2java.springboot.domain.admin_user_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminUserService {

    @Autowired
    private adminUserMapper aum;

    //管理员查询
    public admin_user_info queryAdminUser(String adminName , String adminPw){
        admin_user_info au = aum.query(adminName ,adminPw);
        return au;
    }

    //管理员注册

    //管理员列表

    //管理员添加
    public Boolean addAdminUser(String adminName ,String adminPw){
        aum.add(adminName ,adminPw);
        return true;
    }

}

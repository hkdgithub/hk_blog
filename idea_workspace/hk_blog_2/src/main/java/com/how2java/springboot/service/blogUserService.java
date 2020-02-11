package com.how2java.springboot.service;

import com.how2java.springboot.dao.blogUserMapper;
import com.how2java.springboot.domain.user_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class blogUserService {

    @Autowired
    private blogUserMapper bum;


    //用户添加
    public Boolean addblogUser(String userName ,String userPw){
        bum.add(userName ,userPw);
        return true;
    }

    //查询单个用户
    public user_info queryblogUser(String userName , String userPw){
        user_info bu = bum.query(userName ,userPw);
        return bu;
    }
}

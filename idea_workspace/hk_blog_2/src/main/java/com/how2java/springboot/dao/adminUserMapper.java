package com.how2java.springboot.dao;

import com.how2java.springboot.domain.admin_user_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//管理员用户信息
@Component
@Mapper
public interface adminUserMapper {
    //查询是否有该用户信息
    admin_user_info query(@Param("adminUserName") String adminUserName, @Param("adminUserPw") String adminUserPw);

    //查询所有信息
    // List<User> queryAllUsers();

     //插入用户信息
     int add(@Param("adminUserName") String adminUserName, @Param("adminUserPw") String adminUserPw);
    }




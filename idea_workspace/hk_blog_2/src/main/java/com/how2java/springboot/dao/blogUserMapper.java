package com.how2java.springboot.dao;

import com.how2java.springboot.domain.user_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//博客用户信息
@Component
@Mapper
public interface blogUserMapper {
    //查询是否有该用户信息
    user_info query(@Param("userName") String userName, @Param("userPw") String userPw);

    //查询所有信息
    //List<User> queryAllUsers();

    //插入用户信息
    int add(@Param("userName") String userName, @Param("userPw") String userPw);
}

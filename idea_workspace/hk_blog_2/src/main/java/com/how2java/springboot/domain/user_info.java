package com.how2java.springboot.domain;

import lombok.Data;

//普通用户信息
@Data
public class user_info {
    //用户id
    int userId;
    //用户姓名
    String userName;
    //用户密码
    String userPw;
}

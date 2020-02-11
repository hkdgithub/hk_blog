package com.how2java.springboot.domain;

import lombok.Data;

//管理员用户信息
@Data
public class admin_user_info {

    //管理员id
    int adminUserId;
    //管理员姓名
    String adminUserName;
    //管理员密码
    String adminUserPw;
}

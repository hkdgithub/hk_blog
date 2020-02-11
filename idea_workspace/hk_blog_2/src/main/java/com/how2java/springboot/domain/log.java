package com.how2java.springboot.domain;

import lombok.Data;

@Data
public class log {

    //排名
    int Id;
    //操作
    String operation;
    //操作时间
    String operationTime;
}

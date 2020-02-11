package com.how2java.springboot.service;

import com.how2java.springboot.dao.logMapper;
import com.how2java.springboot.domain.log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class logManageService {

    @Autowired
    private logMapper lm;

    //查询所有的日志
    public List<log> queryAllLog(){
        List<log> list = lm.queryAllLog();
        return list;
    }

    //添加日志
    public Boolean addLog(String operation ,String operationTime){
        lm.addLog(operation ,operationTime);
        return true;
    }

    //删除所有的日志
    public Boolean deleteAllLog(){
        lm.deleteAllLog();
        return true;
    }

}

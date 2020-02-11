package com.how2java.springboot.dao;

import com.how2java.springboot.domain.log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface logMapper {

    //查询所有的日志
    List<log> queryAllLog();

    //添加日志
    int addLog(@Param("operation") String operation ,@Param("operationTime")String operationTime);

    //删除所有的日志
    int deleteAllLog();
}

package com.baizhi.service;

import com.baizhi.entity.AdminLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogServicee {
    void insert(AdminLog adminLog);
    List<AdminLog> queryAll(@Param("start") int start, @Param("rows") int rows);
    List<AdminLog> queryAllLog();
}

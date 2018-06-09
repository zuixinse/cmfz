package com.baizhi.dao;

import com.baizhi.entity.AdminLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDAO {
    void insert(AdminLog adminLog);
    List<AdminLog> queryAll(@Param("start") int start, @Param("rows") int rows);
    List<AdminLog> queryAllLog();
}

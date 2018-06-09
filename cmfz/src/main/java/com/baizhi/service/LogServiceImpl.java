package com.baizhi.service;

import com.baizhi.dao.LogDAO;
import com.baizhi.entity.AdminLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class LogServiceImpl implements LogServicee {
    @Autowired
    private LogDAO logDAO;
    @Override
    public void insert(AdminLog adminLog) {
        logDAO.insert(adminLog);
    }

    @Override
    public List<AdminLog> queryAll(int page, int rows) {
        int start=(page-1)*rows;
        return logDAO.queryAll(start,rows);
    }

    @Override
    public List<AdminLog> queryAllLog() {
        return logDAO.queryAllLog();
    }
}

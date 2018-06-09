package com.baizhi.controller;

import com.baizhi.entity.AdminLog;
import com.baizhi.service.LogServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogServicee logServicee;
    @RequestMapping(value="/queryAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String,Object> queryAll(int page, int rows){
        List<AdminLog> logs = logServicee.queryAll(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",50);
        map.put("rows",logs);
        return  map;
    }
}

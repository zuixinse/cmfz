package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.UserMapDAO;
import com.baizhi.entity.UserMap;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserMapServiceImpl implements UserMapService {
    @Autowired
    private UserMapDAO userMapDAO;

    @Override
    public JSONObject queryMap() {
        List<UserMap> man=userMapDAO.queryMan();
        List<UserMap> women=userMapDAO.queryWomen();
        Map<String,Object> map=new HashMap<>();
        map.put("man",man);
        map.put("women",women);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("man",man);
        jsonObject.put("women",women);
        return jsonObject;
    }
}

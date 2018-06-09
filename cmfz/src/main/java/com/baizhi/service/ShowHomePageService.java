package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;

public interface ShowHomePageService {
    JSONObject showHomePage(String type,String uid,String sub_type);
}

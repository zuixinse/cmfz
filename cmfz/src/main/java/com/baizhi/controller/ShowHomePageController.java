package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.service.ShowHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/first_page")
public class ShowHomePageController {

    @Autowired
    private ShowHomePageService showHomePageService;

    @RequestMapping("/queryHomePage")
    @ResponseBody
    public JSONObject queryHomePage(String type,String uid,String sub_type ){
        JSONObject jsonObject = showHomePageService.showHomePage(type, uid, sub_type);
        return jsonObject;
    }
}

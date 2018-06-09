package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @RequestMapping("/showArticle")
    public JSONObject showArticle(String uid,String id){
        return articleService.queryAllArticle(id,uid);
    }
}

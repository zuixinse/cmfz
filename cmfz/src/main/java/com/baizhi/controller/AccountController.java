package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.ErrorMsg;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService  userService;


    @RequestMapping(value="/login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject login(String account, String password, HttpSession session) {
        return userService.login(account, password, session);
    }
    @RequestMapping("/register")
    public JSONObject register(String phone, String password) {
        return userService.regist(phone, password);

    }

    @RequestMapping("/modify")
    public Object modify(User user){
        try {
            return userService.modify(user);
        }catch (Exception e){
            return new  ErrorMsg("-200","修改失败");
        }
    }
}

package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.User;
import com.baizhi.entity.UserStatistical;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    JSONObject login(String account, String password, HttpSession session);
    User queryByAccount(String account);
    User queryById(String id);
    List<User> queryAll(int page, int rows);
    void freeze(String id);
    void unFreeze(String id);
    List<UserStatistical> queryActive();
    List<User> queryAllUsers();
    JSONObject regist(String phone,String password);
    List<User> queryByRandom(String uid);
    User modify(User user);
}

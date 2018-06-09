package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.UserDAO;
import com.baizhi.dao.UserStatisticalDAO;
import com.baizhi.entity.User;
import com.baizhi.entity.UserStatistical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserStatisticalDAO userStatisticalDAO;

    @Override
    public JSONObject login(String account, String password, HttpSession session) {
        User user1 = userDAO.queryUserByAccount(account);
        JSONObject map=new JSONObject();
        if (user1 == null) {
            map.put("flag", false);
            map.put("message", "用户不存在");
        } else if (!user1.getPassword().equals(password)) {
            map.put("flag", false);
            map.put("message", "密码不正确");
        } else {
            map.put("flag", true);
            map.put("message", "正确");
            map.put("user", user1);
            session.setAttribute("user",user1);
        }
        return map;

    }

    @Override
    public User queryByAccount(String account) {

        return userDAO.queryUserByAccount(account);
    }

    @Override
    public User queryById(String id) {
        return userDAO.queryUserById(id);
    }

    @Override
    public List<User> queryAll(int page, int rows) {
        int start = (page - 1) * rows;
        List<User> list = userDAO.queryAll(start, rows);
        return list;
    }

    @Override
    @LogAnnotation(name="冻结账户")
    public void freeze(String id) {
        userDAO.freeze(id);
    }

    @Override
    @LogAnnotation(name="解除冻结")
    public void unFreeze(String id) {
        userDAO.unFreeze(id);
    }

    @Override
    public List<UserStatistical> queryActive() {
        List<UserStatistical> list=new ArrayList<>();
        for(int i=1;i<=4;i++){
            int count=userStatisticalDAO.queryActive(new Date(),i);
            UserStatistical userStatistical=new UserStatistical("前"+i+"周",count);
            list.add(userStatistical);
        }
        return list;
    }
    @Override
    public List<User> queryAllUsers() {
        return userDAO.queryAllUsers();
    }

    @Override
    public JSONObject regist(String phone,String password ) {
        JSONObject jsonObject=new JSONObject();
        User user=userDAO.queryUserByAccount(phone);
        if(user==null){
            user.setId(UUID.randomUUID().toString());
            user.setAccount(phone);
            user.setPassword(password);
            userDAO.regist(user);
            jsonObject.put("password",password);
            jsonObject.put("uid",user.getId());
            jsonObject.put("phone",phone);
        }else{
            jsonObject.put("error","-200");
            jsonObject.put("errmsg","账号已存在");
        }
        return jsonObject;
    }

    @Override
    public List<User> queryByRandom(String uid) {
        return userDAO.queryByRandom(uid);
    }

    @Override
    public User modify(User user) {
        userDAO.update(user);
        User u=userDAO.queryUserByAccount(user.getAccount());
        return u;
    }
}

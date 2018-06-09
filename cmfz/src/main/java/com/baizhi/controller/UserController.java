package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baizhi.annotation.UserAnnotation;
import com.baizhi.entity.Titles;
import com.baizhi.entity.User;
import com.baizhi.entity.UserStatistical;
import com.baizhi.service.UserMapService;
import com.baizhi.service.UserService;
import com.baizhi.util.CodeUtil;
import com.baizhi.util.ExportExcle;
import com.baizhi.util.SendMsg;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapService userMapService;



    @RequestMapping("/member")
    public  List<User> queryRandom(String uid){
        return  userService.queryByRandom(uid);
    }


    @RequestMapping(value = "/userVli", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> userVli(String account) {
        User user = userService.queryByAccount(account);
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            map.put("flag", false);
            map.put("message", "用户不存在");
        } else {
            map.put("flag", true);
        }
        return map;
    }

    @RequestMapping(value = "/passVli", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> passVli(String account, String password) {
        User user = userService.queryByAccount(account);
        Map<String, Object> map = new HashMap<>();
        if (!user.getPassword().equals(password)) {
            map.put("flag", false);
            map.put("message", "密码错误");
        } else {
            map.put("flag", true);
        }
        return map;
    }

    @RequestMapping(value = "/code", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> code(String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String vcode = (String) session.getAttribute("code");
        if (!code.equals(vcode)) {
            map.put("flag", false);
            map.put("message", "验证码不正确");
        } else {
            map.put("flag", true);
        }
        return map;
    }

    @RequestMapping(value = "/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> queryAll(int page, int rows) {
        List<User> list = userService.queryAll(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("total", 50);
        map.put("rows", list);
        return map;
    }

    @RequestMapping("/freeze")
    @ResponseBody
    public void freeze(String id) {
        userService.freeze(id);
    }

    @RequestMapping(value = "/unFreeze")
    @ResponseBody
    public void unFreeze(String id) {
        userService.unFreeze(id);
    }

    @RequestMapping(value = "/queryActive", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> queryActive() {
        Map<String, Object> map = new HashMap<>();
        List<String> name = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<UserStatistical> list = userService.queryActive();

        for (UserStatistical u : list) {
            name.add(u.getName());
            count.add(u.getCount());
        }
        map.put("name", name);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/queryMap", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void queryMap() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-873d54fa9e354240ac47b6357b280fbe");
        goEasy.publish("sxx_channel", userMapService.queryMap().toJSONString());

    }


    @RequestMapping("/excleEx")
    @ResponseBody
    public void excelExport(String titles, String ids, HttpServletResponse response) throws IOException {
        List<User> users = userService.queryAllUsers();
        ExportExcle.expporet(titles,ids,response,users);
    }


    @RequestMapping(value = "/queryFiled", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> queryFiled() {
        Titles titles = new Titles();
        titles.setText("请选择");
        titles.setId("1");

        List<Titles> children = new ArrayList<>();
        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String title = field.getAnnotation(UserAnnotation.class).name();
            Titles titles1 = new Titles();
            titles1.setText(title);
            titles1.setId(field.getName());
            children.add(titles1);
        }
        titles.setChildren(children);
        Map<String, Object> map = new HashMap<>();
        map.put("titles", titles);
        return map;
    }

}

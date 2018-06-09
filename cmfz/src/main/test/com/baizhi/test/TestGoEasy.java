package com.baizhi.test;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.UserDAO;
import com.baizhi.enume.TypeEnum;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGoEasy {
    @Test
    public void test(){
        /*JSONObject jsonObject=new JSONObject();
        jsonObject.put("a","aasda");
        String s=jsonObject.toJSONString();
        GoEasy goEasy=new GoEasy("http://rest-hangzhou.goeasy.io","BC-873d54fa9e354240ac47b6357b280fbe");
        goEasy.publish("my_channel",s);*/
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
        UserDAO userDAO= (UserDAO) ctx.getBean("userDAO");
        System.out.println(userDAO.queryAll(0,10));
    }

    @Test
    public void test2(){
        System.out.println(TypeEnum.ALL);
    }
}

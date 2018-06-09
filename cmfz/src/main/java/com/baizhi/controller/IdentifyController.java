package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baizhi.util.CodeUtil;
import com.baizhi.util.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/identify")
public class IdentifyController {

    @RequestMapping("/check")
    public JSONObject phoneCode(String phone, String code, HttpSession session){
        Jedis jedis=new Jedis("192.168.145.128",6379);
        jedis.select(0);
        String vcode= jedis.get("phoneCode");
        String phoneSe=jedis.get("phone");
        JSONObject jsonObject=new JSONObject();
        if(vcode==null||phoneSe==null){
            jsonObject.put("result","验证码已失效");
        }
        if(vcode.equals(code)&&phoneSe.equals(phone)){
            jsonObject.put("result","校验成功");
            System.out.println("成功");
        }else{
            jsonObject.put("result","校验失败");
            System.out.println("失败");
        }
        return  jsonObject;
    }


    @RequestMapping("/obtain")
    public void send(String phone) throws ClientException {
        String code=CodeUtil.getCode(4);
        Jedis jedis=new Jedis("192.168.145.128",6379);
        jedis.select(0);
        jedis.setex("phoneCode",60,code);
        jedis.setex("phone",60,phone);
        jedis.close();
        SendMsg.send(phone,code);
    }
}

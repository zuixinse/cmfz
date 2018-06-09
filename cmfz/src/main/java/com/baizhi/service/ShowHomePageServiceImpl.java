package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ImageDAO;
import com.baizhi.dao.SectionDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Image;
import com.baizhi.entity.User;
import com.baizhi.enume.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowHomePageServiceImpl implements ShowHomePageService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private SectionDAO sectionDAO;
    @Autowired
    private ImageDAO imageDAO;
    @Autowired
    private UserDAO userDAO;
    @Override
    public JSONObject showHomePage(String type, String uid, String sub_type) {
        JSONObject jsonObject=new JSONObject();
        if(type.equals("all")){
            List<Image> images=imageDAO.queryAll(0,5);
            List<Album> albums=albumDAO.queryAll(0,6);
            User user=userDAO.queryUserById(uid);
            jsonObject.put("banner",images);
            jsonObject.put("album",albums);
            jsonObject.put("user",user);
        }else if(type.equals("wen")){
            List<Album> albums=albumDAO.queryAll(0,6);
            jsonObject.put("album",albums);
        }else if(type.equals("si")){
            if(sub_type.equals("ssyj")){

            }else if(sub_type.equals("xmfy")){

            }

        }else if(type.equals("me")){
            User user=userDAO.queryUserById(uid);
            jsonObject.put("user",user);
        }else if(type.equals("xiu")){

        }
        return jsonObject;
    }
}

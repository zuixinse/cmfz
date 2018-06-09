package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Album;
import com.baizhi.entity.Section;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/add")
    public void add(MultipartFile pic, Album album, HttpServletRequest request) {
        albumService.insert(pic,album,request);
    }

    @RequestMapping(value = "/queryAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryAll(int page, int rows) {
        List<Album> list = albumService.queryAll(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("total", 50);
        map.put("rows", list);
        return map;
    }
    @RequestMapping(value="/delete")
    public void delete(String id,HttpServletRequest request){
        albumService.delete(id,request);
    }

    @RequestMapping("/queryById")
    public JSONObject queryById(String id,String uid){
        return albumService.queryById(id, uid);
    }


}

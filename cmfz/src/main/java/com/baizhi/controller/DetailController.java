package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.SectionDAO;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private SectionDAO sectionDAO;

    @RequestMapping("/queryAlbum")
    public JSONObject queryAlbum(String id,String uid){
        return albumService.queryById(id,uid);
    }
}

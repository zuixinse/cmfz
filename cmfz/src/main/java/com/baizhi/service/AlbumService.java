package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AlbumService {
    void insert(MultipartFile pic, Album album, HttpServletRequest request);
    void delete(String id,HttpServletRequest request);
    void update(Album album);

    JSONObject queryById(String id, String uid);
    List<Album> queryAll(@Param(value = "start") int start, @Param(value = "rows") int rows);

}

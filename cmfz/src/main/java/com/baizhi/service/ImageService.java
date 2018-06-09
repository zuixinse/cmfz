package com.baizhi.service;

import com.baizhi.entity.Image;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ImageService {
    List<Image> queryAll(int page, int rows);
    Image queryById(int id);
    void insert(Image image);
    void delete(int id, HttpServletRequest request);
    void update(Image image);
}

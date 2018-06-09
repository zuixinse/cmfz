package com.baizhi.dao;

import com.baizhi.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageDAO {
    List<Image> queryAll(@Param("start") int start, @Param("rows") int rows);
    Image queryById(int id);
    void insert(Image image);
    void delete(int id);
    void update(Image image);
}

package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {
    void insert(Album album);
    void delete(String id);
    void update(Album album);
    Album queryById(String id);
    List<Album> queryAll(@Param(value="start")int start,@Param(value="rows") int rows);
}

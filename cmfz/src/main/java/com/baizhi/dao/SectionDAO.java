package com.baizhi.dao;

import com.baizhi.entity.Section;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SectionDAO {
    void insert(Section section);
    void delete(String id);
    void update(Section section);
    List<Section> queryAll();
    Section queryById(String id);
    void deleteAll(String album_id);
    List<Section> queryByAlbum_id(String id);
}

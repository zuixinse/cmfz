package com.baizhi.service;

import com.baizhi.entity.Section;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SectionService {
    void insert(MultipartFile sec, Section section, HttpServletRequest request);
    void delete(String id,String album_id,HttpServletRequest request);
    void update(Section section);
    List<Section> queryAll();
    Section queryById(String id);
}

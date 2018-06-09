package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.ImageDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageDAO imageDAO;

    @Override
    public List<Image> queryAll(int page, int rows) {
        int start=(page-1)*rows;
        return imageDAO.queryAll(start, rows);
    }

    @Override
    public Image queryById(int id) {
        return imageDAO.queryById(id);
    }

    @Override
    @LogAnnotation(name="添加轮播图")
    public void insert(Image image) {
        imageDAO.insert(image);
    }

    @Override
    @LogAnnotation(name="删除轮播图")
    public void delete(int id, HttpServletRequest request) {
        Image image = imageDAO.queryById(id);
        File file = new File(new File(request.getSession().getServletContext().getRealPath("/")).getParent() + image.getUrl());
        if (file.exists())
            file.delete();
        imageDAO.delete(id);
    }

    @Override
    @LogAnnotation(name="更改轮播图状态")
    public void update(Image image) {
        imageDAO.update(image);
    }
}

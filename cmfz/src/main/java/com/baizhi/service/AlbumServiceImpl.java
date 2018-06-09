package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.SectionDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Section;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private SectionDAO sectionDAO;

    @Override
    @LogAnnotation(name="添加专辑")
    public void insert(MultipartFile pic, Album album, HttpServletRequest request) {
        String projectPath=request.getSession().getServletContext().getRealPath("/");
        String oldName=pic.getOriginalFilename();
        String proFile=new File(projectPath).getParent();
        File uploadFile=new File(proFile+"/upload");
        if(!uploadFile.exists()){
            uploadFile.mkdir();
        }
        UUID uuid=UUID.randomUUID();
        String newName=uuid.toString();
        String suffixName=FilenameUtils.getExtension(oldName);
        newName=newName+"."+suffixName;
        try {
            album.setOldName(oldName);
            album.setId(uuid.toString());
            album.setDate(new Date());
            album.setTeacher_id("1");
            album.setBroadcaster("王猛师兄");
            album.setImage("/upload/"+newName);
            albumDAO.insert(album);

            pic.transferTo(new File(uploadFile,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @LogAnnotation(name="删除专辑")
    public void delete(String id,HttpServletRequest request) {
        List<Section> list=sectionDAO.queryByAlbum_id(id);
        String fileName = new File(request.getSession().getServletContext().getRealPath("/")).getParent() ;

        for(Section s:list){
            fileName+=s.getUrl();
            File file=new File(fileName);
            if(file.exists()){
                file.delete();
            }
        }
        sectionDAO.deleteAll(id);
        albumDAO.delete(id);
    }

    @Override
    public void update(Album album) {
        albumDAO.update(album);
    }



    @Override
    public JSONObject queryById(String id, String uid) {
        Album album = albumDAO.queryById(id);
        List<Section> list=sectionDAO.queryByAlbum_id(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("introduction",album);
        jsonObject.put("list",list);
        return jsonObject;
    }

    @Override
    public List<Album> queryAll(int page, int total) {
        int start=(page-1)*total;
        return albumDAO.queryAll(start,total);
    }
}

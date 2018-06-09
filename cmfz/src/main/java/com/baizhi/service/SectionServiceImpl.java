package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.SectionDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Section;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDAO sectionDAO;
    @Autowired
    private AlbumDAO albumDAO;

    @Override
    @LogAnnotation(name="添加章节")
    public void insert(MultipartFile sec, Section section, HttpServletRequest request) {
        /*CommonsMultipartFile cf = (CommonsMultipartFile) sec;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File f2 = fi.getStoreLocation();
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(f2);
            long ls = m.getDuration();
            System.out.println(ls / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
       /* CommonsMultipartFile cf= (CommonsMultipartFile)sec;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File f = fi.getStoreLocation();
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(sec);*/
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        String oldName = sec.getOriginalFilename();
        String proFile = new File(projectPath).getParent();
        File uploadFile = new File(proFile + "/audio");

        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
        UUID uuid = UUID.randomUUID();
        String newName = uuid.toString();
        String suffixName = FilenameUtils.getExtension(oldName);
        newName = newName + "." + suffixName;
        try {
            section.setId(uuid.toString());
            section.setDate(new Date());
            section.setTime(60);
            section.setOldName(oldName);
            String substring = oldName.substring(0, oldName.lastIndexOf("."));
            section.setName(substring);
            section.setUrl("/audio/" + newName);

            sectionDAO.insert(section);
            Album album1 = albumDAO.queryById(section.getAlbum_id());
            album1.setCount(album1.getCount() + 1);
            albumDAO.update(album1);
            sec.transferTo(new File(uploadFile, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    @LogAnnotation(name="删除章节")
    public void delete(String id, String album_Id, HttpServletRequest request) {
        Section section = sectionDAO.queryById(id);
        File file = new File(new File(request.getSession().getServletContext().getRealPath("/")).getParent() + section.getUrl());
        if (file.exists())
            file.delete();
        Album album = albumDAO.queryById(album_Id);
        album.setCount(album.getCount() - 1);
        albumDAO.update(album);
        sectionDAO.delete(id);
    }

    @Override
    public void update(Section section) {
        sectionDAO.update(section);
    }

    @Override
    public List<Section> queryAll() {
        return sectionDAO.queryAll();
    }

    @Override
    public Section queryById(String id) {
        return sectionDAO.queryById(id);
    }
}

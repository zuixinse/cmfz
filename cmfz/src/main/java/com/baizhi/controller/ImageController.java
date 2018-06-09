package com.baizhi.controller;

import com.baizhi.entity.Image;
import com.baizhi.service.ImageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @RequestMapping(value="/queryAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String,Object> queryAll(int page, int rows){
        List<Image> list=imageService.queryAll(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",10);
        map.put("rows",list);
        return map;
    }
    @RequestMapping("/add")
    @ResponseBody
    public void add(MultipartFile pic, Image image,boolean sttt, HttpServletRequest request) throws IOException {
        String projetPath = request.getSession().getServletContext().getRealPath("/");

        File file = new File(projetPath);
        //web项目的路径
        String webappsPath = file.getParent();
        //上传文件夹的路径
        File uploadPath = new File(webappsPath + "/upload");
        //创建上传文件夹
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }


        //获取原始文件名  1.jpg
        String oldFilename = pic.getOriginalFilename();
        //获取后缀名
        String extension = FilenameUtils.getExtension(oldFilename);

        UUID uuid = UUID.randomUUID();
        String newName = uuid.toString();
        newName = newName + "." + extension;
        if(sttt)
            image.setStatus("1");
        else
            image.setStatus("0");
        image.setDate(new Date());
        image.setOldName(oldFilename);
        image.setUrl("/upload/"+newName);
        try {
            //上传到指定的文件夹
            imageService.insert(image);

            pic.transferTo(new File(uploadPath.getPath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/update")
    @ResponseBody
    public void update(int id ,String  status) {

        Image image=imageService.queryById(id);
        image.setStatus(status);
        imageService.update(image);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public void delete(int id,HttpServletRequest request) {
        System.out.println(id);
        imageService.delete(id,request);
    }
}

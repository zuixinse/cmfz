package com.baizhi.controller;

import com.baizhi.entity.Section;
import com.baizhi.service.SectionService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @RequestMapping("/add")
    public void add(MultipartFile sec, Section section, HttpServletRequest request) {

        sectionService.insert(sec, section, request);
    }

    @RequestMapping("/download")
    public void download(String url, String name, HttpServletRequest request, HttpServletResponse response) {
        String proPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(new File(proPath).getParent() + url);
        String fileName = null;
        try {
            fileName = new String(name.getBytes("UTF-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;fileName=" + fileName + url.substring(url.lastIndexOf(".")));
        response.setContentType("audio/mpeg");
        //响应出去
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delete")
    public void delete(String id, String album_id,HttpServletRequest request) {
        sectionService.delete(id, album_id,request);
    }

}

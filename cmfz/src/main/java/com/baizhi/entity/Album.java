package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {
    private String id;
    private String name;
    private String teacher_id;
    private String broadcaster;
    private int count;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date date;
    private String info;
    private String image;
    private int score;
    private String oldName;
    private List<Section> children;

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", broadcaster='" + broadcaster + '\'' +
                ", count=" + count +
                ", date=" + date +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                ", score=" + score +
                ", oldName='" + oldName + '\'' +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public List<Section> getChildren() {
        return children;
    }

    public void setChildren(List<Section> children) {
        this.children = children;
    }

    public Album(String id, String name, String teacher_id, String broadcaster, int count, Date date, String info, String image, int score, String oldName, List<Section> children) {

        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
        this.broadcaster = broadcaster;
        this.count = count;
        this.date = date;
        this.info = info;
        this.image = image;
        this.score = score;
        this.oldName = oldName;
        this.children = children;
    }

    public Album() {

    }
}

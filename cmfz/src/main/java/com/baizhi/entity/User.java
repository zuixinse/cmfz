package com.baizhi.entity;

import com.baizhi.annotation.UserAnnotation;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @UserAnnotation(name="编号")
    private String id;
    @UserAnnotation(name="账号")
    private String account;
    @UserAnnotation(name="姓名")
    private String name;
    @UserAnnotation(name="法号")
    private String dharmaName;
    @UserAnnotation(name="密码")
    private String password;
    @UserAnnotation(name="性别")
    private boolean sex;
    @UserAnnotation(name="省份")
    private String pro;
    @UserAnnotation(name="城市")
    private String city;
    @UserAnnotation(name="头像")
    private String image;
    @UserAnnotation(name="签名")
    private String sign;
    @UserAnnotation(name="上师")
    private int teacher_id;
    @UserAnnotation(name="状态")
    private int status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @UserAnnotation(name="注册日期")
    private Date date;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", pro='" + pro + '\'' +
                ", city='" + city + '\'' +
                ", image='" + image + '\'' +
                ", sign='" + sign + '\'' +
                ", teacher_id=" + teacher_id +
                ", status=" + status +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User(String id, String account, String name, String dharmaName, String password, boolean sex, String pro, String city, String image, String sign, int teacher_id, int status, Date date) {

        this.id = id;
        this.account = account;
        this.name = name;
        this.dharmaName = dharmaName;
        this.password = password;
        this.sex = sex;
        this.pro = pro;
        this.city = city;
        this.image = image;
        this.sign = sign;
        this.teacher_id = teacher_id;
        this.status = status;
        this.date = date;
    }

    public User() {

    }
}

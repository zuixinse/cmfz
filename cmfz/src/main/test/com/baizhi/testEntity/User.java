package com.baizhi.testEntity;

import com.baizhi.annotation.UserAnnotation;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @UserAnnotation(name = "id")
    private String id;
    @UserAnnotation(name = "姓名")
    private String name;
    @UserAnnotation(name = "密码")
    private String password;
    @UserAnnotation(name = "日期")
    private Date date;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User() {

    }

    public User(String id, String name, String password, Date date) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.date = date;
    }
}

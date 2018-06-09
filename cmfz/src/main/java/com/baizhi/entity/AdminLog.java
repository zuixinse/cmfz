package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AdminLog implements Serializable {
    private String id;
    private String name;
    private String method;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String flag;

    @Override
    public String toString() {
        return "AdminLog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", date=" + date +
                ", flag='" + flag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminLog adminLog = (AdminLog) o;
        return Objects.equals(id, adminLog.id) &&
                Objects.equals(name, adminLog.name) &&
                Objects.equals(method, adminLog.method) &&
                Objects.equals(date, adminLog.date) &&
                Objects.equals(flag, adminLog.flag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, method, date, flag);
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public AdminLog(String id, String name, String method, Date date, String flag) {

        this.id = id;
        this.name = name;
        this.method = method;
        this.date = date;
        this.flag = flag;
    }

    public AdminLog() {

    }
}

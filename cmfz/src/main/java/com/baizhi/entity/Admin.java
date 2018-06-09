package com.baizhi.entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable {
    private String id;
    private String name;
    private String password;
    private int rool;
    private int status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return rool == admin.rool &&
                status == admin.status &&
                Objects.equals(id, admin.id) &&
                Objects.equals(name, admin.name) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, rool, status);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", rool=" + rool +
                ", status=" + status +
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

    public int getRool() {
        return rool;
    }

    public void setRool(int rool) {
        this.rool = rool;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Admin(String id, String name, String password, int rool, int status) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.rool = rool;
        this.status = status;
    }

    public Admin() {

    }
}

package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private int id;
    private String title;
    private String iconCls;
    private int parent_id;
    private String url;
    private List<Menu> menus;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", parent_id=" + parent_id +
                ", url='" + url + '\'' +
                ", menus=" + menus +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Menu() {

    }

    public Menu(int id, String title, String iconCls, int parent_id, String url, List<Menu> menus) {

        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.parent_id = parent_id;
        this.url = url;
        this.menus = menus;
    }
}

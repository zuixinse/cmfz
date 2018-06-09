package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Titles implements Serializable {
    private String text;
    private String id;
    private List<Titles> children;

    @Override
    public String toString() {
        return "Titles{" +
                "title='" + text + '\'' +
                ", id='" + id + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titles titles = (Titles) o;
        return Objects.equals(text, titles.text) &&
                Objects.equals(id, titles.id) &&
                Objects.equals(children, titles.children);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, id, children);
    }

    public String getText() {

        return text;
    }

    public void setText(String title) {
        this.text = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Titles> getChildren() {
        return children;
    }

    public void setChildren(List<Titles> children) {
        this.children = children;
    }

    public Titles(String text, String id, List<Titles> children) {

        this.text = text;
        this.id = id;
        this.children = children;
    }

    public Titles() {

    }
}

package com.baizhi.entity;

import java.io.Serializable;

public class UserMap implements Serializable {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "UserMap{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public UserMap(String name, int value) {

        this.name = name;
        this.value = value;
    }

    public UserMap() {

    }
}

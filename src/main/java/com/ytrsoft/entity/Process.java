package com.ytrsoft.entity;

import javax.swing.*;
import java.util.List;

public class Process {

    private int id;
    private String name;
    private ImageIcon icon;
    private List<Process> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public List<Process> getChildren() {
        return children;
    }

    public void setChildren(List<Process> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", children=" + children +
                '}';
    }
}
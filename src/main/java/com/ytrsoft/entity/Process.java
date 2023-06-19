package com.ytrsoft.entity;

import javax.swing.*;
import java.util.List;

public class Process {

    private int id;
    private int parentId;
    private String name;
    private ImageIcon icon;
    private List<Process> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

}

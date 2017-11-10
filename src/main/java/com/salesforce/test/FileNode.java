package com.salesforce.test;

import java.util.ArrayList;
import java.util.List;

public class FileNode {

    private String name;
    private List<FileNode> parents;
    private List<FileNode> childs;
    private String status;
    private boolean explicit;

    public FileNode(String name) {
        this.name = name;
        parents = new ArrayList<>();
        childs = new ArrayList<>();
        status = null;
        explicit = false;
    }
    
    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileNode> getParents() {
        return parents;
    }

    public void setParents(List<FileNode> parents) {
        this.parents = parents;
    }

    public List<FileNode> getChilds() {
        return childs;
    }

    public void setChilds(List<FileNode> childs) {
        this.childs = childs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return name + "--" + status;
    }

}

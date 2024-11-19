package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.Directory;
public class DeletedFileInfo {
    private String name;
    private String type;
    private String content;
    private Directory Parent;

    public DeletedFileInfo(String name, Directory parent) {
        this.name = name;
        this.Parent = parent;
    }

    public DeletedFileInfo(String name, String type, String content) {
        this.name = name;
        this.type = type;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
    public Directory getParent() {
        return Parent;
    }
}

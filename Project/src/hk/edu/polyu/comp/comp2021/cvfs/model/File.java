package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.*;

public class File extends Entry implements Serializable{
    private Type type;
    private String content;

    public File(Directory parent, String name, String type, String content) {
        super(name,parent);
        this.type = Type.toType(type);
        this.content = content;

        setSize(getInitialSize() + content.length() * 2);
        parent.addSize(parent,getInitialSize() + content.length() * 2);
    }

    public String getType() {
        return type.getType();
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return "File " + getName() + " " + getType() + " " + getSize();
    }
}
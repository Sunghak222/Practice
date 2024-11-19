package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.*;

public class Entry implements Serializable{
    private Directory parent;
    private String name;
    private int size;

    private final static int INITIAL_SIZE = 40;

    public Entry(String name, Directory parent) {
        this.name = name;
        if (name.equals("root")) this.size = 0;
        else this.size = INITIAL_SIZE;
        this.parent = parent;
    }
    /*
    * add size from parent to root.
    * Decreasing size can be done by putting a negative value as amount
    */
    public void addSize(Entry curr, int amount) {
        curr.setSize(curr.getSize() + amount);
        if (curr.getParent() != null) {
            addSize(curr.getParent(), amount);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public int getInitialSize() {
        return INITIAL_SIZE;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public Directory getParent() {
        return parent;
    }
}

package hk.edu.polyu.comp.comp2021.cvfs.controller;

public interface Command {
    void execute();
    void undo();
    void redo();
}
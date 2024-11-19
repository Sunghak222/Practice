package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

public class ChangeDirCommand implements Command {
    private CVFS cvfs;
    private String dirName;
    private String previousDir;

    public ChangeDirCommand(CVFS cvfs, String dirName) {
        this.cvfs = cvfs;
        this.dirName = dirName;
        this.previousDir = cvfs.getCurrDir().getName();
    }

    @Override
    public void execute() {
        cvfs.changeCurrentDirectory(dirName);
    }

    @Override
    public void undo() {
        cvfs.changeCurrentDirectory(previousDir);
    }

    @Override
    public void redo() {
        execute();
    }
}

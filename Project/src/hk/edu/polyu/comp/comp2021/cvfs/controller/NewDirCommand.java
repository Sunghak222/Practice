package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.*;

public class NewDirCommand implements Command {
    private CVFS cvfs;
    private String dirName;

    public NewDirCommand(CVFS cvfs, String dirName) {
        this.cvfs = cvfs;
        this.dirName = dirName;
    }

    @Override
    public void execute() {
        cvfs.newDir(dirName);
    }

    @Override
    public void undo() {
        cvfs.deleteFile(dirName);
    }

    @Override
    public void redo() {
        execute();
    }
}

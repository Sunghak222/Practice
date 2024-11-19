package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

public class RenameCommand implements Command {
    private CVFS cvfs;
    private String oldName;
    private String newName;

    public RenameCommand(CVFS cvfs, String oldName, String newName) {
        this.cvfs = cvfs;
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public void execute() {
        cvfs.renameFile(oldName, newName);
    }

    @Override
    public void undo() {
        cvfs.renameFile(newName, oldName);
    }

    @Override
    public void redo() {
        execute();
    }
}

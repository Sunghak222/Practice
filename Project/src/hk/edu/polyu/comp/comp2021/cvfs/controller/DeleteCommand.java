package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;
import hk.edu.polyu.comp.comp2021.cvfs.model.Directory;
import hk.edu.polyu.comp.comp2021.cvfs.model.File;

public class DeleteCommand implements Command {
    private CVFS cvfs;
    private String fileName;

    public DeleteCommand(CVFS cvfs, String fileName) {
        this.cvfs = cvfs;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        for (File f : cvfs.getCurrDir().getFiles()) {
            if (f.getName().equals(fileName)) {
                cvfs.getDeletedFilesStack().add(new DeletedFileInfo(f.getName(),f.getType(),f.getContent()));
            }
        }
        for (Directory d: cvfs.getCurrDir().getChildDir()) {
            if (d.getName().equals(fileName)) {
                cvfs.getDeletedFilesStack().add(new DeletedFileInfo(d.getName(),d.getParent()));
            }
        }
        cvfs.deleteFile(fileName);
    }

    @Override
    public void undo() {
        cvfs.restoreFile();
    }

    @Override
    public void redo() {
        execute();
    }
}

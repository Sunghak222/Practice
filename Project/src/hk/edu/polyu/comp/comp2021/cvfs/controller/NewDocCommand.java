package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

public class NewDocCommand implements Command {
    private CVFS cvfs;
    private String docName;
    private String docType;
    private String docContent;

    public NewDocCommand(CVFS cvfs, String docName,String docType,String docContent) {
        this.cvfs = cvfs;
        this.docName = docName;
        this.docType = docType;
        this.docContent = docContent;
    }

    @Override
    public void execute() {
        cvfs.newFile(docName,docType,docContent);
    }

    @Override
    public void undo() {
        cvfs.deleteFile(docName);
    }

    @Override
    public void redo() {
        execute();
    }
}

package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

public class NewSimpleCriCommand implements Command {
    private CVFS cvfs;
    private String criName;
    private String attrName;
    private String op;
    private Object val;
    private boolean isNegated;

    public NewSimpleCriCommand(CVFS cvfs, String criName, String attrName, String op, Object val, boolean isNegated) {
        this.cvfs = cvfs;
        this.criName = criName;
        this.attrName = attrName;
        this.op = op;
        this.val = val;
        this.isNegated = isNegated;
    }

    @Override
    public void execute() {
        cvfs.newSimpleCriteria(criName, attrName, op, val);
    }

    @Override
    public void undo() {
        cvfs.deleteCriteria(criName);
    }

    @Override
    public void redo() {
        execute();
    }
}


package hk.edu.polyu.comp.comp2021.cvfs.controller;
import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

public class NewNegationCommand implements Command {
    private CVFS cvfs;
    private String criName1;
    private String criName2;

    public NewNegationCommand(CVFS cvfs, String criName1, String criName2) {
        this.cvfs = cvfs;
        this.criName1 = criName1;
        this.criName2 = criName2;
    }

    @Override
    public void execute() {
        cvfs.newNegationCriteria(criName1, criName2);
    }

    @Override
    public void undo() {
        cvfs.deleteCriteria(criName1);
    }

    @Override
    public void redo() {
        execute();
    }
}

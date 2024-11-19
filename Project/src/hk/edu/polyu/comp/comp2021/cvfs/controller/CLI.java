package hk.edu.polyu.comp.comp2021.cvfs.controller;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

public class CLI {
    private CVFS cvfs;
    private CommandManager commandManager;

    public CLI() {
        cvfs = new CVFS();
        commandManager = new CommandManager();
    }

    public void start(String[] parts) {
        try {
            switch (parts[0]) {
                case "newDisk":
                    int size = Integer.parseInt(parts[1]);
                    cvfs.newDisk(size);
                    System.out.println("New disk created with size: " + size);
                    break;
                case "newDoc":
                    commandManager.executeCommand(new NewDocCommand(cvfs, parts[1], parts[2], parts[3]));
                    break;
                case "newDir":
                    commandManager.executeCommand(new NewDirCommand(cvfs, parts[1]));
                    break;
                case "delete":
                    commandManager.executeCommand(new DeleteCommand(cvfs, parts[1]));
                    break;
                case "rename":
                    commandManager.executeCommand(new RenameCommand(cvfs, parts[1], parts[2]));
                    break;
                case "changeDir":
                    commandManager.executeCommand(new ChangeDirCommand(cvfs, parts[1]));
                    break;
                case "newSimpleCri":
                    String criName = parts[1];
                    String attrName = parts[2];
                    String op = parts[3];
                    Object val = parts[4];
                    commandManager.executeCommand(new NewSimpleCriCommand(cvfs, criName, attrName, op, val,true));
                    break;
                case "list":
                    cvfs.list(cvfs.getCurrDir());
                    break;
                case "rList":
                    cvfs.rList(cvfs.getCurrDir());
                    break;
                case "search":
                    cvfs.search(parts[1]);
                    break;
                case "rSearch":
                    cvfs.rSearch(parts[1]);
                    break;
                case "newNegation":
                    commandManager.executeCommand(new NewNegationCommand(cvfs, parts[1], parts[2]));
                    break;
                case "IsDocument":
                    cvfs.createIsDocument();
                    break;
                case "printAllCriteria":
                    cvfs.printAllCriteria();
                    break;
                case "save":
                    cvfs.getCurrDisk().saveToPath(cvfs.getCurrDisk().getName(),parts[1]);
                    break;
                case "load":
                    cvfs.getCurrDisk().load(parts[1]);
                    break;
                case "undo":
                    commandManager.undo();
                    break;
                case "redo":
                    commandManager.redo();
                    break;
                default:
                    System.out.println("Unknown command: " + parts[0]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

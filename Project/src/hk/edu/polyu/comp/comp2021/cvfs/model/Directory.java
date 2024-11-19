package hk.edu.polyu.comp.comp2021.cvfs.model;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Directory extends Entry{
    private List<Directory> childDir;
    private List<File> files;
    
    /* Directory(Current working directory, new directory name); */
    public Directory(String name, Directory parent) {
        super(name,parent);
        childDir = new ArrayList<>();
        files = new ArrayList<>();

        if (parent != null)
            parent.addSize(parent,getInitialSize());
    }

    /*if removed successfully, return true, otherwise, false.*/
    public boolean removeFile(File file){
        file.getParent().addSize(file.getParent(),-1*file.getSize());
        return files.remove(file);
    }
    public boolean removeDir(Directory dir){
        dir.getParent().addSize(dir.getParent(),-1*dir.getSize());
        return childDir.remove(dir);
    }


    public List<File> getFiles(){
        return files;
    }

    public List<Directory> getChildDir() {
        return childDir;
    }

    @Override
    public String toString() {
        return "Directory " + getName() + " " + getSize();
    }




    public String saveToPath(String diskName, String path) {
        // Create new file in directory path stated
        Path filePath;
        try {
            filePath = Paths.get(path, diskName); // Creates a readable file path for java.io.
            java.io.File file = filePath.toFile(); // Creates a new file at said path above.
            writeObjToFile(this, file); // See functions below.
        } catch (InvalidPathException ipe) {
            throw new IllegalArgumentException("This is an invalid path.");
        }
        // Returns new path to created file as a string for future reference.
        return filePath.toString();
    }

    // Need path (user input)
    public void load(String path) {
        Path filePath = Paths.get(path);
        java.io.File file = filePath.toFile();
        readFileToObj(file);
    }

    public static void writeObjToFile(Directory obj, java.io.File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Input/Output exception.");
        }
    }

    public void readFileToObj(java.io.File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Directory workingDisk = (Directory) ois.readObject();
            ois.close();
            // workingDisk.loadCurr(); temp. (?)
            // Method to replace parent directory here. I'm not sure what to put, except it can't be a return statement.
        } catch (IOException io) {
            throw new IllegalArgumentException("Error occurred when reading File.");
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalArgumentException("File is not of the class.");
        }
    }

    public Directory loadCurr() {
        return this;
    }

    /* To use this code, it will go like this:
    Directory exampleDirectory;
    String examplePath = "C:/User/name/Desktop";
    String exampleFileName = "filename";

    (Assume exampleDirectory has contents within it.)
    Path exampleSavedPath = exampleDirectory.saveToPath(exampleFileName, examplePath);

    (Assume we need to open the file we saved as a working directory, workingDir.)
    workingDir.load(exampleSavedPath);
    However, to actually load the file to read you need to use loadCurr() because Java is stupid and try-catch doesn't return objects properly, even in finally statements.
    workingDir = workingDir.loadCurr();

    Then, you can get contents from workingDir.

    */
}
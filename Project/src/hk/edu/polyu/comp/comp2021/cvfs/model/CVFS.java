package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.controller.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CVFS {
    private static Directory currDir;
    private static VirtualDisk currDisk;
    private Map<String, Criterion> criteria = new HashMap<>();
    private Stack<DeletedFileInfo> deletedFilesStack;

    public CVFS(){
        deletedFilesStack = new Stack<DeletedFileInfo>();
    }

    public void newDir(String name) {
        int cap = currDisk.getInitialSize();
        if (!checkCapacity(cap)) {
            throw new IllegalArgumentException("Not enough capacity. Only have " + currDisk.getRemainedCapacity() + ".");
        }
        for (Directory d : currDir.getChildDir()) {
            if (d.getName().equals(name))
                throw new IllegalArgumentException("There is already a unique Directory name in this directory");
        }
        Directory temp = new Directory(name,currDir);
        currDir.getChildDir().add(temp);
    }
    public void newFile(String name, String type, String content) {
        //check remained capacity of the disk
        int cap = currDisk.getRemainedCapacity();
        if (!checkCapacity(40+content.length()*2)) {
            throw new IllegalArgumentException("Not enough capacity. Only have " + cap + ".");
        }
        //check if file name is unique in the current directory.
        for (File file : currDir.getFiles()) {
            if (file.getName().equals(name))
                throw new IllegalArgumentException("There is already a unique file name in this directory");
        }

        //check if the type is valid
        if (!Type.isValidType(type))
            throw new IllegalArgumentException("Invalid file type: " + type + ". Type must be one of 'txt', 'java', 'css', or 'html'.");

        //check if file name has at most 10 characters (and no special characters)
        if (name.length() > 10) {
            throw new IllegalArgumentException("Invalid file name: Must be 10 characters or less.");
        }

        System.out.println("File " + name + " has been created in directory.");
        File ret = new File(currDir,name,type,content);
        currDir.getFiles().add(ret);
    }
    public boolean checkCapacity(int addAmount) {
        int cap = currDisk.getRemainedCapacity();
        return (cap >= addAmount);
    }
    public void renameFile(String oldFileName, String newFileName) {
        for (File f : currDir.getFiles()){
            if (f.getName().equals(newFileName)){
                throw new IllegalArgumentException("New file name already exists.");
            }
        }
        for (Directory d : currDir.getChildDir()){
            if (d.getName().equals(newFileName)){
                throw new IllegalArgumentException("New file name already exists.");
            }
        }
        for (File f : currDir.getFiles()){
            if (f.getName().equals(oldFileName)){
                if (newFileName.length() > 10) {
                    throw new IllegalArgumentException("Invalid new file name: Must be 10 characters or less.");
                }
                f.setName(newFileName);
                System.out.println("In current directory - renamed "+ oldFileName + " to " + newFileName + ".");
                return;
            }
        }
        for (Directory d : currDir.getChildDir()){
            if (d.getName().equals(oldFileName)){
                if (newFileName.length() > 10) {
                    throw new IllegalArgumentException("Invalid new file name: Must be 10 characters or less.");
                }
                d.setName(newFileName);
                System.out.println("In current directory - renamed "+ oldFileName + " to " + newFileName + ".");
                return;
            }
        }
        throw new IllegalArgumentException("There is no file name with this name.");
    }

    public void newDisk(int size){
        currDisk = new VirtualDisk(size);
        currDir = currDisk;
    }
    public void deleteFile(String fileName){
        for (File f : currDir.getFiles()) {
            if (f.getName().equals(fileName)) {
                currDir.removeFile(f);
                return;
            }
        }
        for (Directory dir : currDir.getChildDir()) {
            if (dir.getName().equals(fileName)) {
                currDir.removeDir(dir);
                return;
            }
        }
        throw new IllegalArgumentException("File not found.");
    }
    public void restoreFile() {
        if (!deletedFilesStack.isEmpty()) {
            DeletedFileInfo fileInfo = deletedFilesStack.pop();
            if (fileInfo.getType() == null) {
                newDir(fileInfo.getName());
            }
            else {
                newFile(fileInfo.getName(), fileInfo.getType(), fileInfo.getContent());
            }
        } else {
            throw new IllegalArgumentException("No file to restore.");
        }
    }

    public void list(Directory Dir){
        int tnumb = 0, tsize= 0;
        for(File f: Dir.getFiles()){
            System.out.println(f);
            tnumb++;
            tsize += f.getSize();
        }
        for (Directory d: Dir.getChildDir()) {
            System.out.println(d);
            tnumb++;
            tsize += d.getSize();
        }
        System.out.println("Total number:"+tnumb);
        System.out.println("Total size:"+tsize);
    }
    public void rListIn(Directory dir, String ind){
        System.out.println(ind + "Directory: " + dir.getName());
        list(dir);
        for(Directory cdir : dir.getChildDir()){
            rListIn(cdir,ind+" ");
        }
    }
    public void rList(Directory dir){
        rListIn(dir ,"");
    }

    public void changeCurrentDirectory(String dirName) {
        Directory target = null;

        //find parent directory
        if (dirName.equals("..")) {
            target = currDir.getParent();
            if (target == null) throw new IllegalArgumentException("Already in the root directory.");
        }
        //find child directory named dirName.
        else {
            int n = currDir.getChildDir().size();
            if (n == 0) throw new IllegalArgumentException("There is no directory in the current path.");

            for (int i = 0; i < n; i++) {
                Directory curr = currDir.getChildDir().get(i);
                if (curr.getName().equals(dirName)) {
                    target = curr;
                    break;
                }
            }
        }

        //move to the target directory
        if (target != null) {
            currDir = target;
        }
        else throw new IllegalArgumentException("There exist no such directory in the current path.");
    }
    public void createIsDocument() {
        criteria.put("isDocument", Criterion.getIsDocument());
    }
    public void newSimpleCriteria(String criName, String attrName, String op, Object val) {
        if (isValidCriterion(criName, attrName, op, val)) {
            if (val instanceof String) {
                Criterion criterion = new Criterion(criName, attrName, op, (String)val, false);
                criteria.put(criName, criterion);
            }
            else {
                Criterion criterion = new Criterion(criName, attrName, op, (Integer)val, false);
                criteria.put(criName, criterion);
            }
        }
        else throw new IllegalArgumentException("Invalid criteria.");
    }
    public void newNegationCriteria(String criName1, String criName2) {
        Criterion criterion = findCriteria(criName2);
        if (criterion == null) {
            throw new IllegalArgumentException(criName2 + " not found, skipping negation criteria creation.");
        }

        Criterion newCriterion;
        if (criterion.getBoolIsDocumentCriterion()) {
            newCriterion = new Criterion(false);
        }
        else if (criterion instanceof BinaryCriterion) {
            newCriterion = new BinaryCriterion(criterion.getCriName(),
                    ((BinaryCriterion)criterion).getOperand1(),
                    ((BinaryCriterion)criterion).getOperand2(),
                    criterion.getOp(),true);
        }
        else if (criterion.getVal() instanceof String) {
            newCriterion = new Criterion(criName1,criterion.getAttrName(),criterion.getOp(),
                    (String)criterion.getVal(),true);
        }
        else {
            newCriterion = new Criterion(criName1,criterion.getAttrName(),criterion.getOp(),
                    (Integer)criterion.getVal(),true);
        }

        criteria.put(criName1, newCriterion);
    }
    public void newBinaryCriteria(String criName1, String criName3, String logicOp, String criName4) {
        Criterion operand1 = findCriteria(criName3);
        if (operand1 == null) {
            throw new IllegalArgumentException(criName3 + " not found, skipping negation criteria creation.");
        }
        Criterion operand2 = findCriteria(criName4);
        if (operand2 == null) {
            throw new IllegalArgumentException(criName4 + " not found, skipping negation criteria creation.");
        }
        BinaryCriterion binaryCriterion = new BinaryCriterion(criName1, operand1, operand2, logicOp, false);
        criteria.put(criName1, binaryCriterion);
    }
    /*
     * Check if this criterion is valid.
     */
    public boolean isValidCriterion(String criName, String attrName, String op, Object val) {
        if (criName == null || attrName == null || op == null) return false;
        if (attrName.equals("Name") && op.equals("contains") && isValidVal(val))
            return true;
        if (attrName.equals("Type") && op.equals("equals") && isValidVal(val))
            return true;
        if (attrName.equals("Size") && isValidOp(op) && isValidVal(val))
            return true;
        return false;
    }
    public void deleteCriteria(String criName) {
        if (criteria.containsKey(criName)) {
            criteria.remove(criName);
        } else {
            throw new IllegalArgumentException("Criterion not found.");
        }
    }
    /*check if op is among 6 comparing operator*/
    public boolean isValidOp(String op) {
        return (op.equals(">") || op.equals("<") || op.equals(">=") || op.equals("<=") || op.equals("==") || op.equals("!="));
    }

    /*check if val is String and in double quote, or integer*/
    public boolean isValidVal(Object val) {
        if (isNumeric((String)val)) return true;
        if (val instanceof String) {
            int n = ((String)val).length();
            if (((String)val).charAt(0) == '\"' && ((String)val).charAt(n - 1) == '\"' && n > 2) {
                return true;
            }
        }

        return false;
    }
    public Criterion findCriteria(String criName) {
        Criterion criterion = criteria.get(criName);
        return criterion;
    }
    public void printAllCriteria() {
        for (Map.Entry<String, Criterion> entry : criteria.entrySet()) {
            Criterion criterion = entry.getValue();
            System.out.println(criterion);
        }
    }
    public void search(String criName) {
        if (currDir.getFiles() == null && currDir.getChildDir() == null) {
            System.out.println("There exists no file or directory.");
            return;
        }
        Criterion criterion = findCriteria(criName);
        int sz = 0, num = 0;
        for (File f : currDir.getFiles()) {
            if (criterion.isPass(f)) {
                System.out.println(f);
                num++;
                sz += f.getSize();
            }
        }
        for (Directory dir : currDir.getChildDir()) {
            if (criterion.isPass(dir)) {
                System.out.println(dir);
                num++;
                sz += dir.getSize();
            }
        }
        System.out.println("Found " + num + " files in " + sz +".");
    }
    public void rSearch(String criName) {
        Criterion criterion = findCriteria(criName);
        rSearch(criName, currDir,criterion,0,0);
    }
    public void rSearch(String criName, Directory curr, Criterion criterion, int num, int sz) {
        if (curr.getFiles() == null && curr.getChildDir() == null) {
            System.out.println("Found " + num + " files in " + sz +".");
            return;
        }
        for (File f : curr.getFiles()) {
            if (criterion.isPass(f)) {
                System.out.println(f);
                num++;
                sz += f.getSize();
            }
        }
        for (Directory dir : curr.getChildDir()) {
            if (criterion.isPass(dir)) {
                System.out.println(dir);
                num++;
                sz += dir.getSize();
            }
            rSearch(criName, dir, criterion, num, sz);
        }
    }
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public Map<String, Criterion> getCriteria() {
        return criteria;
    }
    public Directory getCurrDir() {
        return currDir;
    }
    public VirtualDisk getCurrDisk() {
        return currDisk;
    }
    public Stack<DeletedFileInfo> getDeletedFilesStack() {
        return deletedFilesStack;
    }
}
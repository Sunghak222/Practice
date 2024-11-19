package hk.edu.polyu.comp.comp2021.cvfs.model;

public class VirtualDisk extends Directory{
    private final int MAX_SIZE;
    private int remainedCapacity;

    public VirtualDisk(int maxSize) {
        super("root",null);
        this.MAX_SIZE = maxSize;
        this.remainedCapacity = MAX_SIZE;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }
    public int getRemainedCapacity() {
        remainedCapacity = MAX_SIZE - super.getSize();
        return remainedCapacity;
    }
}
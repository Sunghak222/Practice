package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VirtualDiskTest {
    CVFS cvfs = new CVFS();

    @Before
    public void setUp() throws Exception {
        cvfs.newDisk(300);

        cvfs.newDir("d1");

        cvfs.newFile("f1","txt","adfasdf");
        cvfs.newFile("f2","txt","af");

        cvfs.newFile("f3","txt","adfasdf");
        cvfs.newFile("f4","txt","af");
    }
    @Test
    public void testGetMaxSize() {
        assertEquals(300,cvfs.getCurrDisk().getMaxSize());
    }

    @Test
    public void testGetRemainedCapacity() {
        assertEquals(300-(54+40+44+54+44),cvfs.getCurrDisk().getRemainedCapacity());
    }
}
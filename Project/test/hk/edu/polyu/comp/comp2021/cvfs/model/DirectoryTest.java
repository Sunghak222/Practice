package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectoryTest {
    CVFS cvfs = new CVFS();

    @Before
    public void setUp() throws Exception {
        cvfs.newDisk(300);

        cvfs.newDir("d1");

        cvfs.newFile("f1","txt","adfasdfasdf");
        cvfs.newFile("f2","txt","afasfdafdfa");

        cvfs.newSimpleCriteria("A", "Size",">","50");
        cvfs.newSimpleCriteria("B","Name","contains","\"f\"");
        cvfs.newNegationCriteria("notA","A");
        cvfs.newBinaryCriteria("AB", "A","&&","B");
        cvfs.newNegationCriteria("notAB","AB");
    }
    @Test
    public void testRemoveFile() throws Exception {
        cvfs.deleteFile("f1");
    }
    @Test
    public void testRemoveDir() throws Exception {
        cvfs.deleteFile("d1");
    }
    @Test
    public void testToString() {
        System.out.println(cvfs.getCurrDir().toString());
    }
}
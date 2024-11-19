package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CVFSTest {
    CVFS cvfs = new CVFS();

    @Before
    public void setUp() throws Exception {
        cvfs.newDisk(300);

        cvfs.newDir("d1");

        cvfs.newFile("f1","txt","adfasdf");
        cvfs.newFile("f2","txt","af");

        cvfs.changeCurrentDirectory("d1");
        cvfs.newFile("f3","txt","adfasdf");
        cvfs.newFile("f4","txt","af");
        cvfs.changeCurrentDirectory("..");

        cvfs.newSimpleCriteria("A", "Size",">","40");
        cvfs.newSimpleCriteria("B","Name","contains","\"a\"");
        cvfs.newNegationCriteria("notA","A");
        cvfs.newBinaryCriteria("AB", "A","&&","B");
        cvfs.newNegationCriteria("notAB","AB");
    }

    @Test
    public void testCVFSConstructor(){
        CVFS c = new CVFS();

        assert true;
    }

    @Test
    public void testList() {
        cvfs.list(cvfs.getCurrDisk());
    }

    @Test
    public void testRList() {
        cvfs.rList(cvfs.getCurrDisk());
    }
    @Test
    public void testSearch() {
        cvfs.search("A");
    }

    @Test
    public void testRSearch() {
        cvfs.rSearch("A");
    }
    @Test
    public void testChangeCurrentDirectory() {
        cvfs.changeCurrentDirectory("d1");
        cvfs.newFile("f5","txt","dasfasdf");
        cvfs.list(cvfs.getCurrDir());

        assertEquals("d1", cvfs.getCurrDir().getName());

        cvfs.changeCurrentDirectory("..");
        cvfs.rList(cvfs.getCurrDir());
        assertEquals("root", cvfs.getCurrDir().getName());
    }
    @Test
    public void testDeleteCriteria() {
        cvfs.deleteCriteria("A");
    }
    @Test
    public void testPrintAllCriteria() {
        cvfs.printAllCriteria();
    }
}
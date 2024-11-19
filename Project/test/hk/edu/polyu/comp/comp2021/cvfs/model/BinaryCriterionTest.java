package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryCriterionTest {
    Criterion szCri, nameCri;
    File f1,f2;
    BinaryCriterion bcAnd,bcOr;

    @Before
    public void setUp() throws Exception {
        szCri = new Criterion("A", "Size",">",10,false);
        nameCri = new Criterion("B","Name","contains","\"a\"",false);

        f1 = new File(new VirtualDisk(100),"abcd","txt","afasdfs");
        f2 = new File(new VirtualDisk(100),"eeee","txt","af");

        bcAnd = new BinaryCriterion("bc",szCri,nameCri,"&&",false);
        bcOr = new BinaryCriterion("bc",szCri,nameCri,"||",false);
    }

    @Test
    public void testIsPass() {
        assertTrue(bcAnd.isPass(f1));
        assertFalse(bcAnd.isPass(f2));
    }

    @Test
    public void testToString() {
        System.out.println(bcAnd.toString());
    }
}
package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CriterionTest {
    Criterion szCri, nameCri,notSzCri,notNameCri,isDoc,isNotDoc;
    File f1,f2;

    @Before
    public void setUp() throws Exception {
        szCri = new Criterion("A", "Size",">",50,false);
        nameCri = new Criterion("B","Name","contains","\"a\"",false);
        isDoc = new Criterion(true);
        isNotDoc = new Criterion(false);
        notSzCri = new Criterion("A", "Size",">",50,true);
        notNameCri = new Criterion("B", "Name","contains","\"b\"",true);

        f1 = new File(new VirtualDisk(100),"abcd","txt","afasdfs");
        f2 = new File(new VirtualDisk(100),"eeee","txt","af");
    }
    @Test
    public void testIsPass() {
        assertTrue(szCri.isPass(f1));
        assertFalse(szCri.isPass(f2));
        assertTrue(nameCri.isPass(f1));
        assertFalse(nameCri.isPass(f2));

        assertFalse(notSzCri.isPass(f1));
        assertTrue(notSzCri.isPass(f2));
        assertFalse(notNameCri.isPass(f1));
        assertTrue(notNameCri.isPass(f2));

        assertTrue(isDoc.isPass(f1));
        assertFalse(isNotDoc.isPass(f1));
    }

    @Test
    public void testToString() {
        System.out.println(szCri.toString());
    }
}
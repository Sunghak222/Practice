package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Test;

import static hk.edu.polyu.comp.comp2021.cvfs.model.Type.isValidType;
import static hk.edu.polyu.comp.comp2021.cvfs.model.Type.toType;
import static org.junit.Assert.*;

public class TypeTest {

    @Test
    public void testToType() {
        assertEquals(Type.TXT, toType("TXT"));
        assertEquals(Type.TXT, toType("txt"));
    }

    @Test
    public void testIsValidType() {
        assertTrue(isValidType("txt"));
        assertTrue(isValidType("TxT"));
        assertTrue(isValidType("java"));
    }
}
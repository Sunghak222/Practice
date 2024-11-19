package hk.edu.polyu.comp.comp2021.cvfs.view;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class viewTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private view viewInstance;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        viewInstance = new view();
    }

    @Test
    public void testWelcomeMessage() {
        viewInstance.welcomeMessage();
        String expectedOutput = "Welcome to COMP Virtual File System!\n" +
                "Developed by Group 21\n" +
                "20-11-2024\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}

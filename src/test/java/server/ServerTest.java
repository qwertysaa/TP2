package server;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerTest extends TestCase {
@Test
    public void testHandleLoadCourses() {
    try{
        Server testserver = new Server(1337);
        testserver.handleLoadCourses("Automne");
    } catch (IOException e) {
        throw new RuntimeException(e);

    }

    }
}
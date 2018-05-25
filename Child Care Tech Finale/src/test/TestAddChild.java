package test;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class TestAddChild {

    @Before
    public void before()throws Exception{

    }

    @Test
    void testAddChild() throws Exception{
        RMIServer rmiServer = new RMIServer();
        //assertEquals(true, rmiServer.addChild(null, null, null, null, null,null, null, null, null, null, null, null));
        assertTrue(rmiServer.addChild("SSLGL95R088T816M", "6", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "ciaone", null, "Maschio", "ciaone2", "peppe"));

    }

}

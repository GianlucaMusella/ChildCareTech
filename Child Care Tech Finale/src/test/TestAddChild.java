package test;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class TestAddChild {
    RMIServer rmiServer = new RMIServer();

    public TestAddChild() throws RemoteException {
    }


    @Before
    public void before()throws Exception{
        rmiServer.addChild("SSLGL95R088T816M", "6", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "ciaone", null, "Maschio", "ciaone2", "peppe");
    }

    @Test
    void testAddChild() throws Exception{
        //assertEquals(true, rmiServer.addChild(null, null, null, null, null,null, null, null, null, null, null, null));
       // assertTrue(rmiServer.modifyChild("SSLGL95R088T816M", "Lorenzo", "Pallotti", "Civitavecchia", LocalDate.parse("1996-04-09"), "7"));


    }

}

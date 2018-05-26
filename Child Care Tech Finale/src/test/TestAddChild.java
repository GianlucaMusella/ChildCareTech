package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestAddChild {
    private RMIServer rmiServer;

    public TestAddChild() throws RemoteException {

        rmiServer = new RMIServer();
    }


    @BeforeEach
    void addChild()throws Exception{
        rmiServer.addChild("SSLGL95R088T816M", "6", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "ciaone", "ciaone", "Maschio", "ciaone2", "peppe");
    }

    @AfterEach
    void after() throws Exception {
        rmiServer.deleteChild("SSLGL95R088T816M");
    }

    @Test
    void testModifyChild() throws Exception{

//        assertFalse(rmiServer.addChild(null, null, null, null, null,null, null, null, null, null, null, null));
        assertTrue(rmiServer.modifyChild("SSLGL95R088T816M", "Lorenzo", "Pallotti", "Civitavecchia", LocalDate.parse("1996-04-09"), "7"));

    }

}

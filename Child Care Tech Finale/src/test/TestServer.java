package test;


import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestServer {

    @Test
    void testLoginRMI() throws Exception {

        RMIServer rmiServer = new RMIServer();

        assertEquals(false, rmiServer.login(null, null));
        assertEquals(false, rmiServer.login("f", null));
        assertEquals(false, rmiServer.login(null, "atz"));
    }

    @Test
    void testAddChild() throws Exception{
        RMIServer rmiServer = new RMIServer();
        //assertEquals(true, rmiServer.addChild(null, null, null, null, null,null, null, null, null, null, null, null));
        assertEquals(true, rmiServer.addChild("SSLGLC95R08T816M", "6", "GDF", "HAGSFZX", LocalDate.parse("1995-10-08"),"GWRSF", "fewsaf", "ciaone", null, "Maschio", "ciaone2", "peppe"));

    }



}

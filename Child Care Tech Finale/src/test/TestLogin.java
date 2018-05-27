package test;

import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestLogin {

    @Test
    void testLoginFail() throws Exception {

        RMIServer rmiServer = new RMIServer();

        assertFalse(rmiServer.login(null, null));
        assertFalse(rmiServer.login("f", null));
        assertFalse(rmiServer.login(null, "atz"));

    }

    @Test
    void testLoginSuccess() throws Exception {

        RMIServer rmiServer = new RMIServer();

        assertTrue(rmiServer.login("f", "atz"));

    }

}

package test;

import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestLogin {

    private RMIServer rmiServer;

    public TestLogin() throws RemoteException {

        rmiServer = new RMIServer();
    }

    @Test
    void testLoginFail() throws Exception {

        assertFalse(rmiServer.login(null, null));
        assertFalse(rmiServer.login("f", null));
        assertFalse(rmiServer.login(null, "atz"));

    }

    @Test
    void testLoginSuccess() throws Exception {

        assertTrue(rmiServer.login("f", "atz"));

    }

}

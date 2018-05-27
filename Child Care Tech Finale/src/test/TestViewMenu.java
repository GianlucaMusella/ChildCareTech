package test;

import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;

import static org.junit.Assert.assertNotNull;

public class TestViewMenu {

    private RMIServer rmiServer;

    public TestViewMenu() throws RemoteException {

        rmiServer = new RMIServer();
    }

    @Test
    void testViewMenu() throws Exception {
        assertNotNull(rmiServer.viewMenu());
    }

    @Test
    void testViewFirstDish() throws Exception {
        assertNotNull(rmiServer.viewFirst());
    }

    @Test
    void testViewSecondDish() throws Exception {
        assertNotNull(rmiServer.viewSecond());
    }

    @Test
    void testViewSideDish() throws Exception {
        assertNotNull(rmiServer.viewSide());
    }

}

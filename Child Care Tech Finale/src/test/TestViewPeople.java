package test;

import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;

import static org.junit.Assert.assertNotNull;

public class TestViewPeople {

    private RMIServer rmiServer;

    public TestViewPeople() throws RemoteException {

        rmiServer = new RMIServer();
    }

    @Test
    void testViewChild() throws Exception {
        assertNotNull(rmiServer.viewChild());
    }

    @Test
    void testViewParents() throws Exception {
        assertNotNull(rmiServer.viewParents());
    }

    @Test
    void testViewDoctor() throws Exception {
        assertNotNull(rmiServer.viewDoctors());
    }

    @Test
    void testViewContact() throws Exception {
        assertNotNull(rmiServer.viewContacts());
    }

    @Test
    void testViewStaff() throws Exception {
        assertNotNull(rmiServer.viewStaff());
    }

    @Test
    void testViewSupplier() throws Exception {
        assertNotNull(rmiServer.viewSupplier());
    }



}

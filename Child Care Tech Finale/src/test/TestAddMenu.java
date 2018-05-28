package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class TestAddMenu {

    private RMIServer rmiServer;

    public TestAddMenu() throws RemoteException {
        rmiServer = new RMIServer();
    }

    @BeforeEach
    void add() throws Exception {
        rmiServer.addPrimo("test", "ingrediente");
        rmiServer.addAllergy("ingrediente");
        rmiServer.addSecondo("test2", "ingrediente2");
        rmiServer.addAllergy("ingrediente2");
        rmiServer.addSide("test3", "ingrediente3");
        rmiServer.addAllergy("ingrediente3");
        rmiServer.addMenu("testMenu", "test","test1", "test2", LocalDate.parse("2018-08-20"));

    }

    @Test
    void addMenu() throws Exception{
        assertTrue(rmiServer.addMenu("testMenu", "test","test1", "test2", LocalDate.parse("2018-08-20")));
    }

    @AfterEach
    void delete() throws Exception{
        rmiServer.deleteFirst("test");
        rmiServer.deleteSecond("test2");
        rmiServer.deleteSide("test3");
        rmiServer.deleteMenu("testMenu");
    }
}

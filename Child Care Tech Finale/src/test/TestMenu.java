package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class TestMenu {

    private RMIServer rmiServer;

    public TestMenu() throws RemoteException {

        rmiServer = new RMIServer();
    }

    @BeforeEach
    void addMenu() throws Exception {

        rmiServer.addPrimo("provaprimo", "test");
        rmiServer.addSecondo("provaSecondo", "test");
        rmiServer.addSide("provaoContorno", "test");
        rmiServer.addMenu("MenuTest", "provaprimo", "provaSecondo", "provaContorno", LocalDate.parse("2018-05-27"));
    }

    @Test
    void modifyMenu(){
    }
}

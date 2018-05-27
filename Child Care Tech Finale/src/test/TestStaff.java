package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class TestStaff {
    private RMIServer rmiServer;

    public TestStaff() throws RemoteException {

        rmiServer = new RMIServer();
    }

    @BeforeEach
    void addStaff() throws Exception {

        rmiServer.addStaff("test", "test1", "teststafftesttes", LocalDate.parse("1980-08-20"), "Cr", "", "maschio", "bidello");
        rmiServer.addTeacher("test", "test1", "teststaffaccesso", LocalDate.parse("1980-08-30"), "Cr", "", "maschio", "vfouh", "prova", "prova1");

    }

    @Test
    void loginTest() throws Exception {
        assertTrue(rmiServer.login("prova", "prova1"));
    }

    @Test
    void modifyStaff() throws Exception {

        assertTrue(rmiServer.modifyStaff("teststafftesttes", "testa", "mento", "ginj", LocalDate.parse("1980-08-18"), "bidella"));
        assertTrue(rmiServer.modifyStaff("teststaffaccesso", "mento", "testa", "ji", LocalDate.parse("1980-07-18"), "teologa"));
    }

    @AfterEach
    void deleteStaff() throws Exception {
        rmiServer.deleteStaff("teststafftesttes");
        rmiServer.deleteStaff("teststaffaccesso");
    }
}

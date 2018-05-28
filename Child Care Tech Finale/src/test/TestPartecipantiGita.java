package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class TestPartecipantiGita {
    private RMIServer rmiServer;

    public TestPartecipantiGita() throws RemoteException {

        rmiServer = new RMIServer();
    }

    @BeforeEach
    void addTrip() throws Exception {
        rmiServer.addContact("testcontattotest", "Asdrubale", "Pappalardo", "4567356982");
        rmiServer.addParents("testgenitoretest", "Valentina", "Sigismondo",  LocalDate.parse("1994-12-23"), "Napoli", "7895816987", "Femmina");
        rmiServer.addParents("testgenitoretes1", "Francesca", "Sigismondo",  LocalDate.parse("1994-12-23"), "Napoli", "7895816987", "Femmina");
        rmiServer.addDoctor("testpediatratest", "Giovanni", "Mesulla", LocalDate.parse("1965-01-17"), "Napoli", "MAschio");
        rmiServer.newTrip("99", "Test", LocalDate.parse("2018-05-29"), LocalDate.parse("2018-05-29"));
        rmiServer.addChild("testtesttesttest", "99", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "testgenitoretest", "testgenitoretes1", "Maschio", "testpediatratest", "testcontattotest");
        rmiServer.addChild("tasttesttesttest", "99", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "testgenitoretest", "testgenitoretes1", "Maschio", "testpediatratest", "testcontattotest");
        rmiServer.addChild("tosttesttesttest", "99", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "testgenitoretest", "testgenitoretes1", "Maschio", "testpediatratest", "testcontattotest");

    }

    @Test
    void partecipanti() throws Exception {
        assertTrue(rmiServer.newpartecipanteTrip("testtesttesttest", "99"));
        assertTrue(rmiServer.newpartecipanteTrip("tasttesttesttest", "99"));
        assertTrue(rmiServer.newpartecipanteTrip("tosttesttesttest", "99"));
    }

    @Test
    void tappa() throws Exception{
        assertTrue(rmiServer.newTappaServer("Autogrill", "99", LocalDate.parse("2018-05-29")));
    }

    @AfterEach
    void removeTrip() throws Exception{
        rmiServer.deleteTrip("99");
        rmiServer.deleteParents("testgenitoretest");
        rmiServer.deleteParents("testgenitoretes1");
        rmiServer.deleteDoctors("testpediatratest");
        rmiServer.deleteContacts("testcontattotest");
    }
}

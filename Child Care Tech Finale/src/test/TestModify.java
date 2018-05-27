package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serverRMI.server.RMIServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestModify {
    private RMIServer rmiServer;

    public TestModify() throws RemoteException {

        rmiServer = new RMIServer();
    }


    @BeforeEach
    void addChild()throws Exception{
        rmiServer.addContact("testcontattotest", "Asdrubale", "Pappalardo", "4567356982");
        rmiServer.addParents("testgenitoretest", "Valentina", "Sigismondo",  LocalDate.parse("1994-12-23"), "Napoli", "7895816987", "Femmina");
        rmiServer.addParents("testgenitoretes1", "Francesca", "Sigismondo",  LocalDate.parse("1994-12-23"), "Napoli", "7895816987", "Femmina");
        rmiServer.addDoctor("testpediatratest", "Giovanni", "Mesulla", LocalDate.parse("1965-01-17"), "Napoli", "MAschio");
        rmiServer.addChild("testtesttesttest", "99", "GDF", "HAGSFZX", LocalDate.parse("1996-10-08"),"GWRSF", "fewsaf", "testgenitoretest", "testgenitoretes1", "Maschio", "testpediatratest", "testcontattotest");

    }

    @AfterEach
    void after() throws Exception {
        rmiServer.deleteChild("testtesttesttest");
        rmiServer.deleteParents("testgenitoretest");
        rmiServer.deleteParents("testgenitoretes1");
        rmiServer.deleteDoctors("testpediatratest");
        rmiServer.deleteContacts("testcontattotest");
    }

    @Test
    void testModifyChild() throws Exception{

        assertTrue(rmiServer.modifyChild("testtesttesttest", "Lorenzo", "Pallotti", "Civitavecchia", LocalDate.parse("1996-04-09"), "7"));

    }

}

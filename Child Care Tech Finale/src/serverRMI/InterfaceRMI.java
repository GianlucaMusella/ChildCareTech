package serverRMI;

import dataEntry.Child;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface InterfaceRMI extends Remote {

    boolean login(String username, String password) throws Exception;
    void insertChild(Child child) throws RemoteException, SQLException;
}

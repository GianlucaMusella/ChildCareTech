package serverRMI;

import DataEntry.Child;
import DataEntry.Employers;
import DataEntry.Parents;
import DataEntry.Trip;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface InterfaceRMI extends Remote {
    void insertBambino(Child child) throws RemoteException, SQLException;

    void insertPersonaleInterno(Employers employers) throws RemoteException;

    void insertGenitore(Parents parents) throws RemoteException;

    void organizzaGita (Trip trip) throws RemoteException;

    String menuGiorno () throws RemoteException; // String va bene? questa funzione deve ritornare il menù del giorno

}

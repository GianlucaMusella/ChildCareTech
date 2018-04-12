package serverRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {

    void insertBambino() throws RemoteException;

    void insertPersonaleInterno() throws RemoteException;

    void insertGenitore() throws RemoteException;

    void organizzaGita () throws RemoteException;

    void menùGiorno () throws RemoteException; // ho scritto void ma ci va qualcos'altro, deve restituire il menù del giorno

}

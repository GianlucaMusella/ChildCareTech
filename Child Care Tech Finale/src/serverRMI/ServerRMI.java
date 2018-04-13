package serverRMI;

import javax.naming.NamingException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {

        System.out.println("Inizializzo connessione al server RMI");
        InterfaceRMI MainFrame = new RMIServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("MainFrame", MainFrame);
        System.out.println("Server RMI pronto, in attesa di client");

    }
}
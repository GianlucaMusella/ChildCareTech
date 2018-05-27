package serverRMI.client;

import interfaces.InterfaceServer;
import interfaces.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI implements User {

    Registry registry = null;

    public ClientRMI(){

        try {
            registry = LocateRegistry.getRegistry();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public InterfaceServer getUser() throws Exception {
        InterfaceServer Mainframe = null;
        try {
            Mainframe = (InterfaceServer) registry.lookup("MainFrame");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return Mainframe;

    }
}

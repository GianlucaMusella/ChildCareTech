package serverRMI;

import connectionDatabase.ConnectionDatabase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements InterfaceRMI{

    protected RMIServer() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final long serialVersionUID = 1L;

    public boolean login(String username,String password) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        return connectionDatabase.controllo(username, password);
    }
}

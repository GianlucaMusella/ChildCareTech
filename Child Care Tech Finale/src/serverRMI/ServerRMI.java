package serverRMI;

import serverSocket.server.SocketListener;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {

        try {
            System.out.println("Inizializzo connessione al server RMI");
            InterfaceRMI MainFrame = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("MainFrame", MainFrame);
            System.out.println("Server RMI pronto, in attesa di client");
        }catch (RemoteException e){

            e.printStackTrace();
        }

        ServerSocket serverSocket = null;

        try{

            serverSocket = new ServerSocket(3365);
            SocketListener socketListener = new SocketListener(serverSocket);
            System.out.println("Server connection ready");

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Errore");
        }
    }
}
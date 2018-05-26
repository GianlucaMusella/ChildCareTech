package serverRMI.server;

import interfaces.InterfaceServer;
import serverSocket.server.SocketListener;
import serverSocket.server.SocketServer;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {

        try {
            System.out.println("Inizializzo connessione al server RMI");
            InterfaceServer MainFrame = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("MainFrame", MainFrame);
            System.out.println("Server RMI pronto, in attesa di client");
        } catch (RemoteException e) {

            e.printStackTrace();
        }


        ServerSocket serverSocket = null;
        Socket socket = null;
        //int counter = 0;

        try {
            serverSocket = new ServerSocket(3365);
            System.out.println("Server SOCKET listening...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }

        while (true) {
            //counter++;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //new Thread for client
            //System.out.println("Creating new socket thread for client number " + counter + " ...");
            new SocketServer(socket, new RMIServer()).start();  //new thread for client

        }

        /*ServerSocket serverSocket = null;

        try{

            serverSocket = new ServerSocket(3365);
            SocketListener socketListener = new SocketListener(serverSocket);
            System.out.println("Server connection ready");

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Errore");
        }
    }*/
    }
}
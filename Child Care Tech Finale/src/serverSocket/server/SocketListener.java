package serverSocket.server;

import serverRMI.RMIServer;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener extends Thread{

    private final ServerSocket serverSocket;

    public SocketListener(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.start();
    }

    @Override
    public void run(){
        Socket socket;

        while (true){
            try{
                socket = serverSocket.accept();
                SocketServer socketServer = new SocketServer(socket, new RMIServer());
                socketServer.start();

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Errore Connessione");
                break;
            }
        }
    }
}

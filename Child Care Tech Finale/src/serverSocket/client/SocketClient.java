package serverSocket.client;

import serverRMI.InterfaceRMI;
import serverRMI.User;


import java.net.Socket;

public class SocketClient implements User {

    private InterfaceRMI interfaceRMI;

    public SocketClient(){
        Socket socket;

        try{

            System.out.println("Apertura SocketThread");
            socket = new Socket("localhost", 3365);
            System.out.println("SocketThread Aperta");
            System.out.println("Connessione Stabilita");

            System.out.println("Creazione User");
            interfaceRMI = new SocketUserClient(socket);


        }catch (Exception e){

            e.printStackTrace();
            System.err.println("Connection Error");

        }
    }

    @Override
    public InterfaceRMI getUser() throws Exception {
        return interfaceRMI;
    }
}

package serverSocket.client;

import interfaces.InterfaceServer;
import interfaces.User;


import java.net.Socket;

public class SocketClient implements User {

    private InterfaceServer interfaceServer;

    public SocketClient(){
        Socket socket;

        try{

            socket = new Socket("localhost", 3365);
            interfaceServer = new SocketUserClient(socket);

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public InterfaceServer getUser() throws Exception {
        return interfaceServer;
    }
}

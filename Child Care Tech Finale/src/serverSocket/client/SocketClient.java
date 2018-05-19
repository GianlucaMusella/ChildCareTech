package serverSocket.client;

import serverRMI.InterfaceRMI;
import serverRMI.User;


import java.net.Socket;

public class SocketClient implements User {

    private InterfaceRMI interfaceRMI;

    public SocketClient(){
        Socket socket;

        try{

            socket = new Socket("localhost", 3365);
            interfaceRMI = new SocketUserClient(socket);

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public InterfaceRMI getUser() throws Exception {
        return interfaceRMI;
    }
}

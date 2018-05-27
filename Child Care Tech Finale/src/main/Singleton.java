package main;

import serverRMI.client.ClientRMI;
import interfaces.InterfaceServer;
import interfaces.User;
import serverSocket.client.SocketClient;

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null){
            return new Singleton();
        }else
            return instance;
    }

    public InterfaceServer rmiLookup() {
        User user;
        user = new ClientRMI();
        InterfaceServer interfaceServer = null;
        try {
            interfaceServer = user.getUser();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return interfaceServer;
    }

    public InterfaceServer methodSocket() {

        User user;
        user = new SocketClient();

        InterfaceServer interfaceServer = null;
        try {
            interfaceServer = user.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return interfaceServer;
    }
}

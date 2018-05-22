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
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public InterfaceServer rmiLookup() {
        User inte;
        inte = new ClientRMI();
        InterfaceServer rmi = null;
        try {
            rmi = inte.getUser();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rmi;
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

package serverSocket.server;

import serverRMI.InterfaceRMI;
import serverRMI.RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class SocketThread extends Thread{

    private String command = null;
    private ObjectOutputStream outputToClient;
    private ObjectInputStream inputFromClient;
    private java.net.Socket s = null;
    private RMIServer rmiServer;  //mi serve per poter chiamare le funzioni  ESSENZIALE!!!!!

    //devo considerare che per chiamare  i metodi devo mandare dei messaggi

    public SocketThread(Socket s, RMIServer rmiServer) {
        this.rmiServer = rmiServer;
        this.s = s;
    }

    @Override
    public void run(){
        try {

            outputToClient = new ObjectOutputStream(s.getOutputStream());
            inputFromClient = new ObjectInputStream(s.getInputStream());

        } catch (IOException e) {
            System.out.println("IO errore");
        }

        try {
            while (true) {
                System.out.println("Ready to receive a message");
                command = inputFromClient.readUTF();   //attende fino a quando arriva un messaggio

                System.out.println("Received " + command);

                boolean responce = method(command);  //passo il messaggio al doAction che decide cosa fare
                System.out.println(responce);
                outputToClient.writeBoolean(responce);  //manda al socket client la risposta
                outputToClient.flush();
            }

        } catch (IOException e) {
            command = this.getName();  //salva in line il nome del thread che sta eseguendo
            System.out.println("IO Error/ Client " + command + " terminated abruptly");
        } catch (NullPointerException e) {
            command = this.getName();
            System.out.println("Client " + command + " closed");  //stampa nel server che tale client è chiuso
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (inputFromClient != null) {
                    inputFromClient.close();
                    System.out.println("Socket Input Stream Closed");
                }
                if (outputToClient != null) {
                    outputToClient.close();
                    System.out.println("Socket Out Closed");
                }
                if (s != null) {
                    s.close();  //fa terminare la accept di quel solo client
                    //System.out.println(s.isClosed()); //serve per controllare che la socket sia chiusa
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }

    public boolean method(String commandMethod) throws Exception {

        if (commandMethod.equals("login")){
            System.out.println("Sto loggando");
            String user = inputFromClient.readUTF();
            String password = inputFromClient.readUTF();
            System.out.println(user + password);
            boolean success = rmiServer.login(user, password);
            outputToClient.writeBoolean(success);
            System.out.println("Server " + success);
            return success;
        }

        return false;
    }
}

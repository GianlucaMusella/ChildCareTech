package serverSocket;

import connectionDatabase.ConnectionDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler extends Thread implements SocketInterface {

    BufferedReader bufferedReader;
    OutputStream outputStream;
    Socket client;

    public Handler(Socket socketClient) {
        this.client = socketClient;
    }

    public void run(){
        String username, password;
        boolean risposta;

        try{
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            username = bufferedReader.readLine();
            password = bufferedReader.readLine();
            risposta = login(username, password);

            if(risposta)
                username = "true";
            else
                username = "false";

            outputStream = client.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(username);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(String username, String password){
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();

        return  connectionDatabase.controllo(username, password);
    }
}

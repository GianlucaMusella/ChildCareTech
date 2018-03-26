package serverSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server = null;
    private Socket socketClient = null;

    private int porta = 3365;   //porta server

    private DataInputStream in;
    private DataOutputStream out;

    private Socket aspetta(){

        try{
            System.out.println("Inizializzo la connessione al Server");
            //inizializzo
            server = new ServerSocket(porta);

            System.out.println("Server pronto, in ascolto sulla porta " + porta);
            //mi metto sulla porta che ho aperto
            socketClient = server.accept();

            System.out.println("Connessione stabiita");

            //evito onnessioni multiple
            server.close();

            in  = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());

        }catch (IOException e){
            e.printStackTrace();
        }

        return socketClient;
    }

    public static void main(String[] args){
        Server s = new Server();
        s.aspetta();
    }
}

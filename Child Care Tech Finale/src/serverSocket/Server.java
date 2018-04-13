package serverSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server = null;
    private Socket socketClient = null;
    private int porta = 3365;   //porta server

    private Socket inizializzaServer() throws IOException {

        System.out.println("Inizializzo la connessione al Server Socket");
        //inizializzo
        server = new ServerSocket(porta);

        System.out.println("Server Socket pronto, in ascolto sulla porta " + porta);
        //mi metto sulla porta che ho aperto

        while(true) {
            try {
                socketClient = server.accept();
                System.out.println("Connessione stabiita con il client");

            }catch (IOException e){
                e.printStackTrace();
                socketClient.close();
            }
            new Handler(socketClient).start();
        }

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.inizializzaServer();
    }
}

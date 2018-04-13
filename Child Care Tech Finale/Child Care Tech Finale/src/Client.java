import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket mioSocket = null;
    private int porta = 3365;   //porta server
    private DataInputStream in;
    private DataOutputStream out;

    private Socket connetti(){
        try {

            System.out.println("Provo a connettermi al server");
            Socket mioSocket = new Socket(InetAddress.getLocalHost(), porta);
            System.out.println("Connesso");
            in = new DataInputStream(mioSocket.getInputStream());
            out = new DataOutputStream(mioSocket.getOutputStream());

        }catch (UnknownHostException e){

            System.err.println("Host sconosciuto");

        }catch (Exception e){
            System.err.println("Impossibile stabilire la connessione");
        }
        return mioSocket;
    }

    public static void main(String[] args){
        Client c = new Client();
        c.connetti();
    }


}

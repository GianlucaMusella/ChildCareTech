package serverRMI;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

public class ClientRMI {
    public static void main (String [] args) {

        // parte di codice per ricevere i dati da inserire
        // ricevo dei dati di tipo gita, bambino, genitore o personaleinterno

        try {
            InterfaceRMI insertServer = (InterfaceRMI) Naming.lookup("rmi://localhost/InterfaceRMI");

            // comando per inserire effettivamente i dati nel server
            // chiamo la funzione passando come parametro i dati ricevuti nel main

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

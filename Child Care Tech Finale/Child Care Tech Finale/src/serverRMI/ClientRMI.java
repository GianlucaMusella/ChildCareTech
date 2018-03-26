package serverRMI;

import DataEntry.Child;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientRMI {
    public static void main (String [] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        Child c;
        c = new Child ();
        System.out.println("Inserisci il codice fiscale: \n");
        c.setCodicefiscale(input.next()); // Creo il bambino 'c' e setto il suo codice fiscale



        // parte di codice per ricevere i dati da inserire
        // ricevo dei dati di tipo gita, bambino, genitore o personaleinterno

        try {
            InterfaceRMI insertServer = (InterfaceRMI) Naming.lookup("rmi://localhost/InterfaceRMI");
            insertServer.insertBambino(c);

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

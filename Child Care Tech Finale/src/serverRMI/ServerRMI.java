package serverRMI;

import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.*;

public class ServerRMI extends UnicastRemoteObject implements InterfaceRMI{
    public void insertBambino() {

        // bisognerebbe passargli come argomento il riferimento al bambino
        // codice per caricare i dati del bambino sul server

        /*String SQL = new String ("INSERT into Bambino"+this.GetNome+","+this.GetCognome+",");
        Statement stmt = con.CreateStatement ();
        int line = stmt.executeUpdate(SQL);*/

        // questa è solo una bozza. Gli errori che mi da sono probabilmente dovuti al fatto che non è ancora
        // connesso al database. La stringa SQL va comunque sistemata perché mancano tanti dati.
    }

    public void insertPersonaleInterno() { // stesso discorso per passargli il parametro
        // codice per caricare i dati del personale sul server
    }

    public void insertGenitore() {
        // codice per caricare i dati del genitore sul server
    }

    public void organizzaGita () {
        // codice per inserire i dati della gita
    }
    public void menùGiorno () {
        // stesso discorso di prima, va tolto il void. Qui deve restituire una stringa con il menù del giorno
    }

}

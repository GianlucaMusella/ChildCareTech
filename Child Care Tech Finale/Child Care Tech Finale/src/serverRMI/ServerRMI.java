package serverRMI;

import DataEntry.*;

import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;



public class ServerRMI extends UnicastRemoteObject implements InterfaceRMI{
    public ServerRMI () throws RemoteException {

    }
    public void insertBambino(Child child) throws SQLException {

        // bisognerebbe passargli come argomento il riferimento al bambino
        // codice per caricare i dati del bambino sul server

        String SQL = new String ("INSERT into Bambino"+child.getNome()+","+child.getCognome()+",");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb");
        Statement stmt = con.createStatement();
        int line = stmt.executeUpdate(SQL);

        // questa è solo una bozza. Gli errori che mi da sono probabilmente dovuti al fatto che non è ancora
        // connesso al database. La stringa SQL va comunque sistemata perché mancano tanti dati.
    }

    public void insertPersonaleInterno(Employers employers) { // stesso discorso per passargli il parametro
        // codice per caricare i dati del personale sul server
    }

    public void insertGenitore(Parents parents) {
        // codice per caricare i dati del genitore sul server
    }

    public void organizzaGita (Trip trip) {
        // codice per inserire i dati della gita
    }
    public String menuGiorno () {
        // stesso discorso di prima, va tolto il void. Qui deve restituire una stringa con il menù del giorno
        Menu menu;
        String menuGiorno;
        // si crea una stringa in cui viene presentato in modo ordinato il menù
        return menuGiorno;
    }

}

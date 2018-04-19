package serverRMI;

import connectionDatabase.ConnectionDatabase;
import dataEntry.Child;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RMIServer extends UnicastRemoteObject implements InterfaceRMI{

    protected RMIServer() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final long serialVersionUID = 1L;

    public boolean login(String username,String password) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        return connectionDatabase.controllo(username, password);
    }

    @Override
    public void insertChild(Child child) throws SQLException {
        /*Statement statement = ConnectionDatabase.getConnection().createStatement();
        String newChild = "INSERT INTO mydb.bambini (Nome,Cognome, CodiceFiscale) VALUES ('"+child.getNome()+"','"+child.getCognome()+"','"+child.getCodiceFiscale()+"') ";
        int ciao = statement.executeUpdate(newChild);*/

        ConnectionDatabase.getConnection();
        ConnectionDatabase.insertChild(child);
    }


}

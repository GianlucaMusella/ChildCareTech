package serverRMI;

import connectionDatabase.ConnectionDatabase;
import dataEntry.Child;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements InterfaceRMI{

    protected RMIServer() throws RemoteException {
        super();
    }
    private static final long serialVersionUID = 1L;

    @Override
    public boolean login(String username,String password) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String login = "SELECT Username,Password FROM mydb.personaleconaccesso WHERE Username = " + '"' + username + '"' + "AND Password = " + '"' + password + '"';

        try{
            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(login);
            resultSet = preparedStatement.executeQuery();

            ArrayList<String> utente = new ArrayList<>();

            try {
                if(!resultSet.next()){
                    System.out.println("Utente non registrato nel database");
                    return false;
                }else {

                    resultSet.beforeFirst();
                    while(resultSet.next()){

                        utente.add(resultSet.getString("Username"));
                        System.out.println(resultSet.getString("Username"));
                        System.out.println(resultSet.getString("Password"));
                        if(!utente.isEmpty())

                            return true;

                    }

                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try {
                    if (resultSet != null)
                        resultSet.close();      //chiudo le connessioni al db una volta effettuato il controllo
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    if(preparedStatement != null)
                        preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }catch (Exception e){
            System.out.println(e);
        }

        return false;

    }

    @Override
    public boolean newSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String supplier = "INSERT INTO mydb.fornitori (Nome,Cognome,Azienda,TipoDiFornitura,PartitaIVA) VALUES (?,?,?,?,?)";

       // +name+"','"+surname+"','"+azienda+"','"+fornitura+"','"+partitaIva+"') ";

        try{

            ConnectionDatabase connectionDatabase = new ConnectionDatabase();
            preparedStatement = connectionDatabase.initializeConnection().prepareStatement(supplier);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,azienda);
            preparedStatement.setString(4,fornitura);
            preparedStatement.setString(5,partitaIva);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if(preparedStatement != null){
                    preparedStatement.close();      //chiudo le connessioni al db una volta effettuato il controllo
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       /* ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        PreparedStatement preparedStatement = connectionDatabase.initializeConnection().prepareStatement(supplier);
        preparedStatement.executeUpdate();*/
        return false;
    }

    @Override
    public void insertChild(Child child) throws SQLException {
        /*Statement statement = ConnectionDatabase.getConnection().createStatement();
        String newChild = "INSERT INTO mydb.bambini (Nome,Cognome, CodiceFiscale) VALUES ('"+child.getNome()+"','"+child.getCognome()+"','"+child.getCodiceFiscale()+"') ";
        int ciao = statement.executeUpdate(newChild);*/

        /*ConnectionDatabase.getConnection();
        ConnectionDatabase.insertChild(child);*/
    }


}

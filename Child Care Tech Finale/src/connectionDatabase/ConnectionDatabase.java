package connectionDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConnectionDatabase{

    // username per la connessione al database
    private static final String USERNAME = "root";

    //password per la connessione al database
    private static final String PASSWORD = "Giantusso956";

    // jdbc url per MySql.
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";

    private final Connection conn;


    public ConnectionDatabase() {
        conn = login();

        System.out.println("Connessione avvenuta con successo al database");
    }

    private Connection login(){
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /*public boolean autentication(String user){
        String u = "SELECT Username FROM mydb.personaleconaccesso";

        try {

            System.out.println("Verifica credenziali in corso");
            PreparedStatement statement = conn.prepareStatement(u);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<String> username = new ArrayList<>();

            while (username.size() > 0){
                int i = 0;
                String[]a = username.toArray(null);
                System.out.println(a[i]);
                i++;
            }

            while (resultSet.next()){
                username.add(resultSet.getString("Username"));

                if(username.contains(user)){
                    return true;
                }
            }

        }catch (Exception e){
            System.err.println(e);
        }
        return false;
    }*/

    public boolean controllo(String username, String password){

        String u = "SELECT Username,Password FROM mydb.personaleconaccesso WHERE Username = " + '"' + username + '"' + "AND Password = " + '"' + password + '"';

        try{

            PreparedStatement statement = conn.prepareStatement(u);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<String> utente = new ArrayList<>();

            while(resultSet.next()){

                utente.add(resultSet.getString("Username"));
                System.out.println(resultSet.getString("Username"));
                System.out.println(resultSet.getString("Password"));
                if(!utente.isEmpty())

                    return true;

            }

        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

}

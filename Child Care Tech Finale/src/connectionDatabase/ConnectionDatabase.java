package connectionDatabase;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionDatabase{

    // username per la connessione al database
    private static final String USERNAME = "root";

    //password per la connessione al database
    private static final String PASSWORD = "Giantusso956";

    // jdbc url per MySql.
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";

    private Connection conn = null;

    /*static{
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(Exception e) {
            System.out.println(e);
            conn = null;
        }
    }

    public static Connection getConnection(){
        return conn;
    }
*/
    public ConnectionDatabase() {
        conn = initializeConnection();

        System.out.println("Connessione avvenuta con successo al database");
    }

    public Connection initializeConnection(){

        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

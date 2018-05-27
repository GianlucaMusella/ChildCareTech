package connectionDatabase;

import java.sql.*;

public class ConnectionDatabase{

    // username per la connessione al database
    private static final String USERNAME = "root";

    //password per la connessione al database
    private static final String PASSWORD = "Giantusso956";

    // jdbc url per MySql.
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";

    //private static final String driver = "com.mysql.jdbc.Driver";
    private Connection conn = null;


    public ConnectionDatabase() throws Exception {
        try {
            conn = initializeConnection();
            System.out.println("Connessione avvenuta con successo al database");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            conn.close();
            System.out.println("Sto chiudendo connessione al db");
        }
        /*Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

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

    /*public Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }*/
}

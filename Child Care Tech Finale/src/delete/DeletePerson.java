package delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletePerson {
    public void deleteChild (String codiceFiscale) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb");
        Statement stmt = conn.createStatement();
        int i = stmt.executeUpdate("DELETE * FROM BAMBINI WHERE CODICE FISCALE = '"+codiceFiscale+"'");

    }
    public void deleteParent (String codicefiscale) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb");
        Statement stmt = conn.createStatement();
        int i = stmt.executeUpdate("DELETE * FROM GENITORI WHERE CODICEFISCALE = '"+codicefiscale+"'");
    }

    // deletePerson richiede in input due stringhe: il nome della tabella e il codicefiscale da cancellare. In
    // questo modo serve una sola funzione per cancellare

    public void deletePerson (String table, String codiceFiscale) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb");
        Statement stmt = conn.createStatement();
        int i = stmt.executeUpdate("DELETE * FROM "+table+" WHERE CODICE FISCALE = '"+codiceFiscale+"'");

    }
}

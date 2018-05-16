package menuFood;

import connectionDatabase.ConnectionDatabase;

import java.sql.SQLException;
import java.sql.Statement;

public class Check {
    public void Check (MenuGS menuGS) throws SQLException { // c'è MenuGS per non avere errori di compilazione ma servirebbe un tipo con nome, primo e secondo
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("SELECT * FROM mydb.primi WHERE ")
    }
}

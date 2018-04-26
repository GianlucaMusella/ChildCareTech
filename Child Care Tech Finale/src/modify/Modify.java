package modify;

import connectionDatabase.ConnectionDatabase;
import dataEntry.ChildGS;

import java.sql.SQLException;
import java.sql.Statement;

// PARTE SERVER
// RICEVO IN INPUT IL CODICE FISCALE DEL BAMBINO DI CUI CI SONO DA MODIFICARE I DATI E I DATI DEL NUOVO BAMBINO

public class Modify {
    public void modifyChild (String oldcodicefiscale, ChildGS newer) throws SQLException { // chiamato newer e nonnew perché se no lo prendeva come il costruttore
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("UPDATE mydb.bambini SET (CodiceFiscale,idBambino,Nome,Cognome,Data_di_Nascita,Luogo_di_Nascita,) = ('");
        int i = stmt.executeUpdate(SQL + newer.getCodiceFiscale() + "', '" + newer.getIdBambino() + "', '" + newer.getNome() + "', '" + newer.getCognome()
                + "', '" + newer.getData() + "', '" + newer.getLuogoDiNascita() + "' " + "WHERE CodiceFiscale = '" + oldcodicefiscale + "'");

    }
}



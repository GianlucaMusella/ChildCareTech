package pullmanBambini;

import connectionDatabase.ConnectionDatabase;

import java.sql.ResultSet;
import java.sql.Statement;

// viene chiamata quando un viene inserito un nuovo partecipante
public class assegnaPullman {
    // da lato client prende in input l'idigta e il codice fiscale del bambino. Chiamata subito dopo newPartecipante e countPullman
    // LATO SERVER
    public void assegnaPullman (String codicefiscale, String idGita) throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        // int i = 0; // questo sarà il numero del pullman
        String PullmanSQL = ("SELECT * FROM mydb.bambini_has_gita WHERE mydb.bambini_has_gita.Gita_idGIta = ");
        ResultSet rsbis = stmt.executeQuery(PullmanSQL + idGita);
        float counter = 0.0f;
        while (rsbis.next()){
            counter = counter + 1;
        }
        float x = (float) (counter / 1.00);
        int Pullman = (int) Math.ceil(x);
        String SQL = ("UPDATE Bambini_has_gita SET numPullman = " + Pullman + " WHERE Bambini_CodiceFiscale = '" +
                codicefiscale  + "' AND Gita_idGIta = '" + idGita + "'");
        int i = stmt.executeUpdate(SQL);
    }
    public void pullmanGiusto (String codiceFiscale, String idGita, int pullman) {

    }
}

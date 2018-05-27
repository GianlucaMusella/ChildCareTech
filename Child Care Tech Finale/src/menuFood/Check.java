package menuFood;

import connectionDatabase.ConnectionDatabase;
import getterAndSetter.food.BambiniAllergici;
import getterAndSetter.food.MenuGS;
import getterAndSetter.people.ChildGS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* MODIFICHE DATABASE
Va tolto il campo allergie nella tabella bambino. La tabella bambino collegata con una relazione n : m ad allergie. La tabella della relazione
si chiamerà: Bambini_has_Allergeni e i cambi saranno: Allergeni_Nome e Bambini_CodiceFiscale
 */

public class Check {
    public ArrayList<BambiniAllergici> Check (MenuGS menuGS) throws Exception { // c'è MenuGS per non avere errori di compilazione ma servirebbe un tipo con nome, primo e secondo
        ArrayList<BambiniAllergici> values = new ArrayList<>();
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("SELECT  mydb.bambini.Nome, mydb.bambini.Cognome, mydb.Allergeni.Nome, mydb.Primi.Nome, mydb.secondi.Nome, mydb.menumensa.Nome " +
                "FROM (((((( mydb.menumensa " +
                "INNER JOIN mydb.Primi ON mydb.menumensa.Primi_Nome = mydb.primi.Nome)" +
                "INNER JOIN mydb.allergeni_has_primi ON mydb.allergeni_has_primi.Primi_Nome = mydb.primi.nome) " +
                "INNER JOIN mydb.secondi ON mydb.menumensa.Secondi_Nome = mydb.secondi.Nome) " +
                "INNER JOIN mydb.allergeni_has_secondi ON mydb.allergeni_has_secondi.Secondi_Nome = mydb.secondi.nome) " +
                "INNER JOIN mydb.allergeni ON mydb.allergeni_has_primi.Allergeni_Nome = mydb.allergeni.Nome OR mydb.allergeni_has_secondi.Allergeni_Nome = mydb.allergeni.Nome) " +
                "INNER JOIN mydb.bambini ON mydb.bambini.Allergie = mydb.allergeni.Nome) " +
                "WHERE mydb.menumensa.Nome = '" + menuGS.getNomeMenu() + "'");
        ResultSet rs = stmt.executeQuery(SQL);

        System.out.println(rs.getString(1)); // prova per vedere se funziona

        values.add(new BambiniAllergici(rs.getString(1), rs.getString(2), rs.getString(3),
                rs.getString(4), rs.getString(5), rs.getString(6)));
        return values;
    }
}
/*SELECT mydb.bambini.Nome, mydb.bambini.Cognome, mydb.bambini.CodiceFiscale
FROM ((mydb.bambini_has_gita
INNER JOIN mydb.bambini ON mydb.bambini.idBambino = mydb.bambini_has_gita.Bambini_idBambino)
INNER JOIN mydb.gita ON mydb.gita.idGita = mydb.bambini_has_gita.Gita_idGita = 1) */


package menuFood;

import connectionDatabase.ConnectionDatabase;
import getterAndSetter.food.MenuGS;
import getterAndSetter.people.ChildGS;

import java.sql.SQLException;
import java.sql.Statement;

/* MODIFICHE DATABASE
Va tolto il campo allergie nella tabella bambino. La tabella bambino collegata con una relazione n : m ad allergie. La tabella della relazione
si chiamerà: Bambini_has_Allergeni e i cambi saranno: Allergeni_Nome e Bambini_CodiceFiscale
 */

public class Check {
    public void Check (MenuGS menuGS) throws SQLException { // c'è MenuGS per non avere errori di compilazione ma servirebbe un tipo con nome, primo e secondo
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("SELECT mydb.bambini.Nome, mydb.Bambini.Cognome, mydb.Allergeni.Nome, mydb.Primi.Nome, mydb.Secondi.Nome, mydb.menu.Nome" +
                "FROM ((((((( mydb.bambini_has_allergeni" +
                "INNER JOIN mydb.bambini ON mydb.bambini.CodiceFiscale = mydb.bambini_has_allergeni.Bambini_CodiceFiscale)" +
                "INNER JOIN mydb.allergeni ON mydb.allergeni.Nome =  mydb.bambini_has_allergeni.Allergeni_Nome)" +
                "INNER JOIN mydb.allergeni_has_primi ON mydb.allergeni_has_primi.Allergeni_Nome = mydb.allergeni.Nome)" +
                "INNER JOIN mydb.primi ON mydb.primi.Nome = mydb.allergeni_has_primi.Primi_Nome = mydb.primi.Nome)" +
                "INNER JOIN mydb.allergeni_has_secondi ON mydb.allergeni_has_secondi.Allergeni_Nome = mydb.allergeni.Nome)" +
                "INNER JOIN mydb.secondi ON mydb.secondi.Nome = mydb.allergeni_has_secondi.Secondi_Nome = mydb.secondi.Nome)" +
                "INNER JOIN mydb.menu ON mydb.menu.Secondi_Nome = mydb.secondi.Nome AND mydb.menu.Primi_Nome = mydb.primi.Nome AND mydb.menu.Nome = '" + menuGS.getNomeMenu() + "')");
        int n = stmt.executeUpdate(SQL);
    }
}
/*SELECT mydb.bambini.Nome, mydb.bambini.Cognome, mydb.bambini.CodiceFiscale
FROM ((mydb.bambini_has_gita
INNER JOIN mydb.bambini ON mydb.bambini.idBambino = mydb.bambini_has_gita.Bambini_idBambino)
INNER JOIN mydb.gita ON mydb.gita.idGita = mydb.bambini_has_gita.Gita_idGita = 1) */

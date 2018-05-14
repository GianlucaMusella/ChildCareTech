package trip;

import connectionDatabase.ConnectionDatabase;
import javafx.fxml.FXML;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class partecipantiTrip {

    // PARTE CLIENT

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtIDgita;

    public void partecipantiTripC (ActionEvent actionEvent){

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.newpartecipanteTrip(txtCodicefiscale.getText(), txtIDgita.getText());

    }


    // PARTE SERVER

    public void newpartecipanteTrip (String codicefiscale, String idGita) throws SQLException {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SelectSQL = ("SELECT * FROM mydb.bambini WHERE CodiceFiscale = '");
        ResultSet rs = stmt.executeQuery(SelectSQL + codicefiscale + "')");
        int idBambino = rs.getInt("idBambino");
        String InsertSQL = ("INSERTO INTO mydb.bambini_has_gita (Bambini_IdBambino, Gita_idGita) VALUES (");
        int n = stmt.executeUpdate(InsertSQL + idBambino + "," + Integer.parseInt(idGita) + ")");


        // E fino a qui era la parte di inserimento, ora faccio il conto per sapere quanti pullman mi servono

        String CountSQL = ("SELECT COUNT (Bambini_idBambino) FROM mydb.bambini_has_gita");
        ResultSet rsbis = stmt.executeQuery(CountSQL);
        float x = (52/rsbis.getInt(1));
        int NumPullman = (int) Math.ceil(x);
        int i = stmt.executeUpdate("INSERTO INTO mydb.gita (NumPullman) VALUES (" + NumPullman + ")");

    }


}

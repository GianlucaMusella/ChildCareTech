package trip;

import connectionDatabase.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Tappe {
    @FXML
    private TextField txtTappa;
    @FXML
    private TextField txtidGita;
    @FXML
    private TextField txtOra;


    public void newTappa (ActionEvent actionEvent) {
        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.newTappaServer(txtTappa.getText(), Integer.parseInt(txtidGita.getText()), txtOra.getText());

    }
    public void newTappaServer (String tappa, int idGita, String orario) throws SQLException {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("INSERT INTO mydb.tappe (Nome, Gita_idGita, Orario) VAlUES '" + tappa + "', '" + idGita + "', '" + orario + "'");
        int n = stmt.executeUpdate(SQL);
    }

}

package trip;

import connectionDatabase.ConnectionDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

// PARTE CLIENT

public class newTrip {
    @FXML
    private TextField txtMeta;
    @FXML
    private TextField txtID;
    @FXML
    private DatePicker datePartenza;
    @FXML
    private DatePicker dateRitorno;

    public void newTripC (ActiveEvent activeEvent) {

        LocalDate localDate = datePartenza.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date partenza = Date.from(instant);

        LocalDate localDate = dateRitorno.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date ritorno = Date.from(instant);

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.newTrip(txtID.getText(), txtMeta.getText(), partenza, ritorno);

        // i bambini vengono presi con un multiselect dalla tableview
    }


// PARTE SERVER
// Ci sarebbe anche da modificare il database, lo sistemo oggi sul tuo perché dal mio non vorrei incasinare il tutto


    public void newTrip (String id, String meta, Date partenza, Date ritorno) throws SQLException {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Statement stmt = connectionDatabase.initializeConnection().createStatement();
        String SQL = ("INSERT INTO mydb.gita (idGita, Meta, Data_Partenza, Data_Ritorno) VALUES (");
        int idGita = Integer.parseInt(id);
        int n = stmt.executeUpdate(SQL + idGita + "," + meta + "," + partenza + "," + ritorno + ",)");
    }
}



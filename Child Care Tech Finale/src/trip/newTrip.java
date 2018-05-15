package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.time.LocalDate;

// PARTE CLIENT

public class newTrip {
    @FXML
    private TextField txtMetaa;
    @FXML
    private TextField txtID;
    @FXML
    private DatePicker datePartenza;
    @FXML
    private DatePicker dateRitorno;

    public void newTrip (ActionEvent actionEvent) throws Exception {

        LocalDate andata = datePartenza.getValue();
        LocalDate ritorno = dateRitorno.getValue();


        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.newTrip(txtID.getText(), txtMetaa.getText(), andata, ritorno);

    }

}



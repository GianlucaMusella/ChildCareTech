package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Stage {

    @FXML
    private TextField txtIdTappa;

    @FXML
    private TextField txtTappa;

    @FXML
    private DatePicker giornoTappa;

    @FXML
    private TextField txtidGita;

    @FXML
    private TextField txtOra;


    public void newTappa (ActionEvent actionEvent) throws Exception {

        String numeroTappa = txtIdTappa.getText();
        String tappa = txtTappa.getText();
        String idGita = txtidGita.getText();
        LocalDate giorno = giornoTappa.getValue();
        String ora = txtOra.getText();

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        interfaceServer.newTappaServer(numeroTappa, tappa, idGita, giorno, ora);


        txtidGita.clear();
        txtIdTappa.clear();
        txtTappa.clear();
        txtOra.clear();
        giornoTappa.getEditor().clear();
    }


}

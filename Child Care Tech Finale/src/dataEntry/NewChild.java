package dataEntry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class NewChild {

    @FXML
    private Label lblError;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private TextField txtLuogo;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtGenitore1;

    @FXML
    private TextField txtGenitore2;

    @FXML
    private TextField txtPediatra;

    @FXML
    private TextField txtAllergia;

    @FXML
    private TextField txtIDBambino;

    @FXML
    private TextField txtSesso;

    @FXML
    private TableView tableBambini;

    @FXML
    private TableColumn columnNome;

    @FXML
    private TableColumn columnCognome;

    @FXML
    private TableColumn columnCodiceFiscale;

    public void addChild(ActionEvent actionEvent) throws Exception {
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String luogo = txtLuogo.getText();

        LocalDate localDate = dateData.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date data = Date.from(instant);

        String genitore1 = txtGenitore1.getText();
        String genitore2 = txtGenitore2.getText();
        String pediatra = txtPediatra.getText();
        String allergie = txtAllergia.getText();
        String idBambino = txtIDBambino.getText();
        String sesso = txtSesso.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addChild(codiceFiscale, idBambino,  nome,  cognome,  data,  luogo,  allergie,  genitore1,  genitore2, sesso, pediatra);

        //se metti il cambio del label serve la try catch con remoteexception


        txtNome.clear();
        txtCognome.clear();
        txtCodiceFiscale.clear();
        txtLuogo.clear();

        txtGenitore1.clear();
        txtGenitore2.clear();
        txtPediatra.clear();
        txtAllergia.clear();
        txtIDBambino.clear();

    }

}

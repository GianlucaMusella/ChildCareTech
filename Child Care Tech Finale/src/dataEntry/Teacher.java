package dataEntry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.time.LocalDate;

public class Teacher {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtAllergie;

    @FXML
    private TextField txtSesso;

    @FXML
    private TextField txtInsegnante;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    public void addTeacher(ActionEvent actionEvent) throws Exception {

        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        LocalDate data = dateData.getValue();
        String allergie = txtAllergie.getText();
        String sesso = txtSesso.getText();
        String insegnante = txtInsegnante.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();


        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addTeacher(nome, cognome, codiceFiscale, data, allergie, sesso, insegnante, username, password);

        //se metti il cambio del label serve la try catch con remoteexception


        txtNome.clear();
        txtCognome.clear();
        txtCodiceFiscale.clear();
        txtAllergie.clear();

        txtSesso.clear();
        txtInsegnante.clear();
        txtUsername.clear();
        txtPassword.clear();

    }

}

package addPeople;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.time.LocalDate;

public class AddTeacher {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtLuogo;

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

    @FXML
    private Label lblStatus;

    public void addTeacher(ActionEvent actionEvent) throws Exception {
        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtCodiceFiscale.getText().isEmpty() && txtCodiceFiscale.getText().length() == 16 ||
                txtLuogo.getText().isEmpty() || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String nome = txtNome.getText();
            String cognome = txtCognome.getText();
            String codiceFiscale = txtCodiceFiscale.getText();
            LocalDate data = dateData.getValue();
            String luogo = txtLuogo.getText();
            String allergie = txtAllergie.getText();
            String sesso = txtSesso.getText();
            String insegnante = txtInsegnante.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.addTeacher(nome, cognome, codiceFiscale, data, luogo, allergie, sesso, insegnante, username, password);
                if (success) {
                    txtNome.clear();
                    txtCognome.clear();
                    txtCodiceFiscale.clear();
                    txtAllergie.clear();
                    txtLuogo.clear();
                    txtSesso.clear();
                    txtInsegnante.clear();
                    txtUsername.clear();
                    txtPassword.clear();
                    dateData.getEditor().clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/StaffMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Personale Interno");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void menuPrincipale(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Child Care Tech");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}

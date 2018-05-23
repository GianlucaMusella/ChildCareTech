package addPeople;

import getterAndSetter.people.ChildGS;
import getterAndSetter.people.StaffGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStaff implements Initializable{
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
    private RadioButton radioMaschio;

    @FXML
    private RadioButton radioFemmina;

    @FXML
    private TextField txtMansione;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<StaffGS> tabellaStaff;

    @FXML
    private TableColumn<StaffGS, String> colonnaNome;

    @FXML
    private TableColumn<StaffGS, String> colonnaCognome;

    @FXML
    private TableColumn<StaffGS, String> colonnaCF;

    @FXML
    private TableColumn<StaffGS, String> colonnaMansione;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCF.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        colonnaMansione.setCellValueFactory(new PropertyValueFactory<>("mansione"));

        tabellaStaff.getItems().clear();
    }


    public void addStaff(ActionEvent actionEvent) throws Exception {

        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        LocalDate data = dateData.getValue();
        String luogo = txtLuogo.getText();
        String allergie = txtAllergie.getText();
        String mansione = txtMansione.getText();
        String sesso;
        String sessoM = radioMaschio.getText();
        String sessoF = radioFemmina.getText();

        if (radioMaschio.isSelected()) {
            sesso = sessoM;
        } else {
            sesso = sessoF;
        }

        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtCodiceFiscale.getText().isEmpty() && txtCodiceFiscale.getText().length() == 16 ||
                txtLuogo.getText().isEmpty() || txtMansione.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {

            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.addStaff(nome, cognome, codiceFiscale, data, luogo, allergie, sesso, mansione);

                if (success) {
                    txtNome.clear();
                    txtCognome.clear();
                    txtCodiceFiscale.clear();
                    txtAllergie.clear();
                    txtLuogo.clear();
                    txtMansione.clear();
                    dateData.getEditor().clear();
                    lblStatus.setText("Inserimento riuscito");
                    lblStatus.setTextFill(Color.BLACK);
                }
            } catch (Exception e ){
                e.printStackTrace();
            }
        }
    }

    public void viewStaff(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;

        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        ArrayList<StaffGS> staffGS = interfaceServer.viewStaff();

        tabellaStaff.setColumnResizePolicy(tabellaStaff.CONSTRAINED_RESIZE_POLICY);
        tabellaStaff.setItems(FXCollections.observableArrayList(staffGS));

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

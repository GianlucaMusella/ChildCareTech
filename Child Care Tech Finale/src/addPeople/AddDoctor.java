package addPeople;

import getterAndSetter.people.DoctorGS;
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

public class AddDoctor implements Initializable{

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private TextField txtLuogo;

    @FXML
    private DatePicker dataData;

    @FXML
    private RadioButton radioMaschio;

    @FXML
    private RadioButton radioFemmina;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<DoctorGS> tabellaPediatra;

    @FXML
    private TableColumn<DoctorGS, String> colonnaNomeP;

    @FXML
    private TableColumn<DoctorGS, String> colonnaCognomeP;

    @FXML
    private TableColumn<DoctorGS, String> colonnaCfP;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNomeP.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognomeP.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCfP.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaPediatra.getItems().clear();
    }

    public void addDoctor(ActionEvent actionEvent) throws Exception {

        String sesso;
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String luogo = txtLuogo.getText();
        LocalDate data = dataData.getValue();
        String sessoM = radioMaschio.getText();
        String sessoF = radioFemmina.getText();

        if (radioMaschio.isSelected()) {
            sesso = sessoM;
        } else {

            sesso = sessoF;
        }

        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtCodiceFiscale.getText().isEmpty() || txtLuogo.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            try {
                InterfaceServer interfaceServer;

                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }

                boolean success = interfaceServer.addDoctor(codiceFiscale, nome, cognome, data, luogo, sesso);

                if (success) {
                    txtNome.clear();
                    txtCognome.clear();
                    txtCodiceFiscale.clear();
                    txtLuogo.clear();
                    dataData.getEditor().clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void viewDoctor(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;

        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        ArrayList<DoctorGS> doctorGS = interfaceServer.viewDoctors();

        tabellaPediatra.setColumnResizePolicy(tabellaPediatra.CONSTRAINED_RESIZE_POLICY);
        tabellaPediatra.setItems(FXCollections.observableArrayList(doctorGS));

    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/DoctorMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Pediatra");
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

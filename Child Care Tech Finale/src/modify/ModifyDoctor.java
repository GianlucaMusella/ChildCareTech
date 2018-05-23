package modify;

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
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyDoctor implements Initializable {

    @FXML
    private TextField txtCodicefiscaleOld;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtLuogo;

    @FXML
    private DatePicker dateData;

    @FXML
    private TableView<DoctorGS> tabellaPediatra;

    @FXML
    private TableColumn<DoctorGS, String> colonnaNome;

    @FXML
    private TableColumn<DoctorGS, String> colonnaCodiceFiscale;

    @FXML
    private Label lblStatus;

    public void modifyDoctor (ActionEvent actionEvent) throws Exception {

        String codiceFiscale = txtCodicefiscaleOld.getText();
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String luogo = txtLuogo.getText();
        LocalDate data = dateData.getValue();

        if (txtCodicefiscaleOld.getText().isEmpty() || txtCodicefiscaleOld.getText().length() != 16 || txtCognome.getText().isEmpty() || txtNome.getText().isEmpty() || txtLuogo.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {

            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                interfaceServer.modifyDoctor(codiceFiscale, nome, cognome, luogo, data);

                lblStatus.setTextFill(Color.BLACK);
                lblStatus.setText("Inserimento riuscito");
                txtCodicefiscaleOld.clear();
                txtNome.clear();
                txtCognome.clear();
                txtLuogo.clear();
                dateData.getEditor().clear();
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaPediatra.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaPediatra.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscaleOld.setText(newSelection.getCodiceFiscale());
            }
        });

        tabellaPediatra.getItems().clear();
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
}

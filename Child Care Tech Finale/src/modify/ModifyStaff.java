package modify;

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
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyStaff implements Initializable{

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
    private TextField txtMansione;

    @FXML
    private TableView<StaffGS> tabellaStaff;

    @FXML
    private TableColumn<StaffGS, String> colonnaNome;

    @FXML
    private TableColumn<StaffGS, String> colonnaCognome;

    @FXML
    private TableColumn<StaffGS, String> colonnaCodiceFiscale;

    @FXML
    private TableColumn<StaffGS, String> colonnaMansione;

    @FXML
    private Label lblStatus;

    public void modifyStaff(ActionEvent actionEvent) throws Exception {

        String codiceFiscale = txtCodicefiscaleOld.getText();
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String luogo = txtLuogo.getText();
        LocalDate data = dateData.getValue();
        String mansione = txtMansione.getText();

        if (txtCodicefiscaleOld.getText().isEmpty() || txtCodicefiscaleOld.getText().length() != 16 || txtCognome.getText().isEmpty() || txtNome.getText().isEmpty() || txtMansione.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                interfaceServer.modifyStaff(codiceFiscale, nome, cognome, luogo, data, mansione);

                lblStatus.setTextFill(Color.BLACK);
                lblStatus.setText("Inserimento riuscito");
                txtCodicefiscaleOld.clear();
                txtNome.clear();
                txtCognome.clear();
                txtLuogo.clear();
                dateData.getEditor().clear();
                txtMansione.clear();
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        colonnaMansione.setCellValueFactory(new PropertyValueFactory<>("mansione"));

        tabellaStaff.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaStaff.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscaleOld.setText(newSelection.getCodiceFiscale());
            }
        });

        tabellaStaff.getItems().clear();
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
        stage.setScene(scene);
        stage.show();
    }

}

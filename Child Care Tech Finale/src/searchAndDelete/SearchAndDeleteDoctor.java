package searchAndDelete;

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
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchAndDeleteDoctor implements Initializable{

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtNome;

    @FXML
    private TableView<DoctorGS> tabellaPediatra;

    @FXML
    private TableColumn<DoctorGS, String> columnNome;

    @FXML
    private TableColumn<DoctorGS, String> colonnaCognome;

    @FXML
    private TableColumn<DoctorGS, String> columnCodicefiscale;

    @FXML
    private Button show;

    @FXML
    private Label lblStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaPediatra.getItems().clear();

        show.fire();
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


    public void searchDoctor(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<DoctorGS> doctorGS = interfaceServer.searchDoctors(txtNome.getText(), txtCodicefiscale.getText());

        tabellaPediatra.setColumnResizePolicy(tabellaPediatra.CONSTRAINED_RESIZE_POLICY);
        tabellaPediatra.setItems(FXCollections.observableArrayList(doctorGS));
    }


    public void deleteDoctor(ActionEvent actionEvent) throws Exception {
        if (tabellaPediatra.getSelectionModel().isEmpty()){
            lblStatus.setText("ERRORE: nessun elemento selezionato");
        }
        else {
            DoctorGS deletableDoctors = tabellaPediatra.getSelectionModel().getSelectedItem();
            int index = tabellaPediatra.getSelectionModel().getSelectedIndex();
            String codiceFiscale = deletableDoctors.getCodiceFiscale();

            System.out.println(codiceFiscale); // Ho messo questo per capire se prende il codice fiscale giusto

            InterfaceServer interfaceServer;
            if (Controller.selection.equals("RMI")) {
                interfaceServer = Singleton.getInstance().rmiLookup();
            } else {
                interfaceServer = Singleton.getInstance().methodSocket();
            }
            boolean success = interfaceServer.deleteDoctors(codiceFiscale);
            if (success) {
                tabellaPediatra.getItems().remove(index);
            }
        }
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

    public void logout(ActionEvent ev) throws Exception {
        ((Node) ev.getSource()).getScene().getWindow().hide();
        Controller controller = new Controller();
        Stage stage = new Stage();
        stage.setTitle("Child Care Tech");
        stage.setResizable(false);
        controller.initialize(stage);
    }
}

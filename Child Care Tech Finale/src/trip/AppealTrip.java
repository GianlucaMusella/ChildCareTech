package trip;

import getterAndSetter.trip.AppealGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppealTrip implements Initializable{

    @FXML
    private TextField idGita;

    @FXML
    private TableView<AppealGS> tableAppello;

    @FXML
    private TableColumn<AppealGS, String> columnNome;

    @FXML
    private TableColumn<AppealGS, String> columnCognome;

    @FXML
    private TableColumn<AppealGS, String> columnCodicefiscale;

    @FXML
    private TableColumn<AppealGS, String> columnPresenza;

    @FXML
    private TableColumn <AppealGS, String> columnPullman;

    @FXML
    private TextField txtPullman;

    @FXML
    private Label lblStatus;

    private InterfaceServer interfaceServer;

    public AppealTrip(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnPresenza.setCellValueFactory(new PropertyValueFactory<>("presenza"));
        columnPullman.setCellValueFactory(new PropertyValueFactory<>("pullman"));


        tableAppello.getItems().clear();
    }


    public void loadData(ActionEvent actionEvent) throws Exception {

        if(idGita.getText().isEmpty()) {
            lblStatus.setText("Errore: dati mancanti");
        } else {
            ArrayList<AppealGS> appelloGSArrayList = interfaceServer.loadDataServer(Integer.parseInt(idGita.getText()));  //Qui vado a chiamare la parte Server che scrivo in fondo al codice come ieri

            tableAppello.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableAppello.setItems(FXCollections.observableArrayList(appelloGSArrayList));
        }
    }

    public void appelloTrip (ActionEvent actionEvent) throws Exception {


        if(idGita.getText().isEmpty() || txtPullman.getText().isEmpty()) {
            lblStatus.setText("Errore: dati mancanti");
        } else {

            AppealGS bambinoPresente = tableAppello.getSelectionModel().getSelectedItem();
            String codiceFiscale = bambinoPresente.getCodiceFiscale();

            interfaceServer.bambinoPresenteServer(codiceFiscale, Integer.parseInt(idGita.getText()), Integer.parseInt(txtPullman.getText()));
        }

    }

    public void assenza (ActionEvent actionEvent) throws Exception {

        if (idGita.getText().isEmpty()) {
            lblStatus.setText("Errore: dati mancanti");
        } else {
            AppealGS bambinoAssente = tableAppello.getSelectionModel().getSelectedItem();
            String codiceFiscale = bambinoAssente.getCodiceFiscale();
            interfaceServer.bambinoAssenteServer(codiceFiscale, Integer.parseInt(idGita.getText()));
        }
    }

    public void assenzaAll (ActionEvent actionEvent) throws Exception {

        if (idGita.getText().isEmpty()) {
            lblStatus.setText("Errore: dati mancanti");
        } else {
            interfaceServer.assenzaAll(Integer.parseInt(idGita.getText()));
        }
    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripManagement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Gestione Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

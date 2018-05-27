package trip;

import getterAndSetter.people.ChildGS;
import getterAndSetter.trip.TripGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TripPartecipant implements Initializable{

    @FXML
    private TableView<ChildGS> tableBambini;

    @FXML
    private TableColumn<ChildGS, String> columnNome;

    @FXML
    private TableColumn<ChildGS, String> columnCognome;

    @FXML
    private TableColumn<ChildGS, String> columnCodiceFiscale;

    @FXML
    private TableColumn<ChildGS, String> columnID;

    @FXML
    private TableView<TripGS> tableGita;

    @FXML
    private TableColumn<TripGS, String> columnIDGita;

    @FXML
    private TableColumn<TripGS, String> columnMeta;

    @FXML
    private TableColumn<TripGS, String> columnAndata;

    @FXML
    private TableColumn<TripGS, String> columnRitorno;

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtIDgita;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("idBambino"));

        columnIDGita.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMeta.setCellValueFactory(new PropertyValueFactory<>("meta"));
        columnAndata.setCellValueFactory(new PropertyValueFactory<>("andata"));
        columnRitorno.setCellValueFactory(new PropertyValueFactory<>("ritorno"));

        tableBambini.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableBambini.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscale.setText(newSelection.getCodiceFiscale());
            }
        });

        tableGita.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableGita.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtIDgita.setText(newSelection.getId());
            }
        });

        tableBambini.getItems().clear();
        tableGita.getItems().clear();
    }

    public void partecipantiTrip (ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        interfaceServer.newpartecipanteTrip(txtCodicefiscale.getText(), txtIDgita.getText());
        interfaceServer.pullmanCount(txtIDgita.getText());

        txtCodicefiscale.clear();
        txtIDgita.clear();

    }

    public void viewInfo(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        
        ArrayList<ChildGS> childrenGS = interfaceServer.viewChild();
        ArrayList<TripGS> tripGS = interfaceServer.viewTrip();

        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGS));

        tableGita.setColumnResizePolicy(tableGita.CONSTRAINED_RESIZE_POLICY);
        tableGita.setItems(FXCollections.observableArrayList(tripGS));

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

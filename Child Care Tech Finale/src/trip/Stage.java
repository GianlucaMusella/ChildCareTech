package trip;

import getterAndSetter.trip.TripGS;
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
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Stage implements Initializable{

    @FXML
    private TextField txtTappa;

    @FXML
    private DatePicker giornoTappa;

    @FXML
    private TextField txtidGita;

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
    private Button show;

    @FXML
    private Label lblStatus;

    private InterfaceServer interfaceServer;

    public Stage(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnIDGita.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMeta.setCellValueFactory(new PropertyValueFactory<>("meta"));
        columnAndata.setCellValueFactory(new PropertyValueFactory<>("andata"));
        columnRitorno.setCellValueFactory(new PropertyValueFactory<>("ritorno"));

        tableGita.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableGita.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtidGita.setText(newSelection.getId());
            }
        });

        tableGita.getItems().clear();
        show.fire();
    }

    public void viewInfo(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<TripGS> tripGS = interfaceServer.viewTrip();

        tableGita.setColumnResizePolicy(tableGita.CONSTRAINED_RESIZE_POLICY);
        tableGita.setItems(FXCollections.observableArrayList(tripGS));

    }


    public void newTappa (ActionEvent actionEvent) throws Exception {

        String tappa = txtTappa.getText();
        String idGita = txtidGita.getText();
        LocalDate giorno = giornoTappa.getValue();

        if (txtTappa.getText().isEmpty() || txtidGita.getText().isEmpty() || giornoTappa.getValue() == null || giornoTappa.getValue() == null) {
            lblStatus.setText("ERRORE: Inserire dati obbligatori");
        }else {
            try {
                boolean success = interfaceServer.newTappaServer(tappa, idGita, giorno);

                if (success) {
                    txtidGita.clear();
                    txtTappa.clear();
                    giornoTappa.getEditor().clear();
                }
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }
    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripManagement.fxml"));
        Scene scene = new Scene(root);
        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.setTitle("Gestione Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

package trip;

import getterAndSetter.trip.TripGS;
import interfaces.InterfaceServer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TripOrganize implements Initializable {

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
    private TableColumn<TripGS, String> colonnaPullman;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnIDGita.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMeta.setCellValueFactory(new PropertyValueFactory<>("meta"));
        columnAndata.setCellValueFactory(new PropertyValueFactory<>("andata"));
        columnRitorno.setCellValueFactory(new PropertyValueFactory<>("ritorno"));
        colonnaPullman.setCellValueFactory(new PropertyValueFactory<>("pullman"));

        tableGita.getItems().clear();

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

    public void deleteTrip(ActionEvent actionEvent) throws Exception {

        TripGS deletableTrips = tableGita.getSelectionModel().getSelectedItem();
        String idGita = deletableTrips.getId();

        System.out.println(idGita); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        interfaceServer.deleteTrip(idGita);

    }

    public void createTrip(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/AddTrip.fxml"));
        Scene scene = new Scene(root);
        javafx.stage.Stage stage = new Stage();
        stage.setTitle("Inserisci Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void back_method(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

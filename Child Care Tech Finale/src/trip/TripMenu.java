package trip;


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
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TripMenu implements Initializable{

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

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<TripGS> tripGS = interfaceRMI.viewTrip();

        tableGita.setColumnResizePolicy(tableGita.CONSTRAINED_RESIZE_POLICY);
        tableGita.setItems(FXCollections.observableArrayList(tripGS));

    }

    public void createTrip (ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/AddTrip.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void deleteTrip(ActionEvent actionEvent) throws Exception {

        TripGS deletableTrips = tableGita.getSelectionModel().getSelectedItem();
        String idGita = deletableTrips.getId();

        System.out.println(idGita); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.deleteTrip(idGita);
    }

    public void appelloTrip (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/AppealTrip.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void pullman (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/Pullman.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void tappe (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/Stage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void partecipantiTrip (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/partecipantiTrip.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void logout(ActionEvent ev) throws Exception {
        ((Node) ev.getSource()).getScene().getWindow().hide();
        Controller controller = new Controller();
        Stage stage = new Stage();
        controller.initialize(stage);
    }

}

package searchAndDelete;

import getterAndSetter.StaffGS;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import loginScreen.Controller;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchAndDeleteStaff implements Initializable{

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TableView<StaffGS> tabellaStaff;

    @FXML
    private TableColumn<StaffGS, String> columnNome;

    @FXML
    private TableColumn<StaffGS, String> columnCognome;

    @FXML
    private TableColumn<StaffGS, String> columnCodicefiscale;

    @FXML
    private TableColumn<StaffGS, String> columnMansione;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnMansione.setCellValueFactory(new PropertyValueFactory<>("mansione"));

        tabellaStaff.getItems().clear();

    }

    public void viewStaff(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<StaffGS> staffGS = interfaceRMI.viewStaff();

        tabellaStaff.setColumnResizePolicy(tabellaStaff.CONSTRAINED_RESIZE_POLICY);
        tabellaStaff.setItems(FXCollections.observableArrayList(staffGS));
    }


    public void searchStaff(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<StaffGS> staffGS = interfaceRMI.searchStaff(txtNome.getText(), txtCognome.getText(), txtCodicefiscale.getText());

        tabellaStaff.setColumnResizePolicy(tabellaStaff.CONSTRAINED_RESIZE_POLICY);
        tabellaStaff.setItems(FXCollections.observableArrayList(staffGS));
    }


    public void deleteStaff (ActionEvent actionEvent) throws Exception {

        StaffGS deletableStaff = tabellaStaff.getSelectionModel().getSelectedItem();
        String codiceFiscale = deletableStaff.getCodiceFiscale();

        System.out.println(codiceFiscale); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.deleteStaff(codiceFiscale);


    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/StaffMenu.fxml"));
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

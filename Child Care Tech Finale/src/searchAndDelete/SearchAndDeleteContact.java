package searchAndDelete;

import getterAndSetter.people.ContactGS;
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
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchAndDeleteContact implements Initializable{

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtNome;

    @FXML
    private TableView<ContactGS> tabellaContatti;

    @FXML
    private TableColumn<ContactGS, String> columnNome;

    @FXML
    private TableColumn<ContactGS, String> columnCognome;

    @FXML
    private TableColumn<ContactGS, String> columnCodicefiscale;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaContatti.getItems().clear();

    }

    public void viewContact(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<ContactGS> contactGS = interfaceServer.viewContacts();

        tabellaContatti.setColumnResizePolicy(tabellaContatti.CONSTRAINED_RESIZE_POLICY);
        tabellaContatti.setItems(FXCollections.observableArrayList(contactGS));
    }


    public void searchContacts(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<ContactGS> contactGS = interfaceServer.searchContacts(txtNome.getText(), txtCodicefiscale.getText());

        tabellaContatti.setColumnResizePolicy(tabellaContatti.CONSTRAINED_RESIZE_POLICY);
        tabellaContatti.setItems(FXCollections.observableArrayList(contactGS));
    }


    public void deleteContacts(ActionEvent actionEvent) throws Exception {

        ContactGS deletableContacts = tabellaContatti.getSelectionModel().getSelectedItem();
        int index = tabellaContatti.getSelectionModel().getSelectedIndex();
        String codiceFiscale = deletableContacts.getCodiceFiscale();

        System.out.println(codiceFiscale); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        interfaceServer.deleteContacts(codiceFiscale);
        tabellaContatti.getItems().remove(index);


    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ContactMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Contatti");
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

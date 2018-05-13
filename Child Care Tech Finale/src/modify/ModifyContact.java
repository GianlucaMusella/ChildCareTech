package modify;

import getterAndSetter.people.Contact;
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
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyContact implements Initializable{

    @FXML
    private TextField txtCodicefiscaleOld;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TableView<Contact> tabellaContatti;

    @FXML
    private TableColumn<Contact, String> colonnaNome;

    @FXML
    private TableColumn<Contact, String> colonnaCodiceFiscale;

    public void modifyContact(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.modifyContact(txtCodicefiscaleOld.getText(), txtNome.getText(), txtCognome.getText(), txtTelefono.getText());

        txtCodicefiscaleOld.clear();
        txtNome.clear();
        txtCognome.clear();
        txtTelefono.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaContatti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaContatti.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscaleOld.setText(newSelection.getCodiceFiscale());
            }
        });

        tabellaContatti.getItems().clear();
    }

    public void viewContact(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<Contact> contacts = interfaceRMI.viewContacts();

        tabellaContatti.setColumnResizePolicy(tabellaContatti.CONSTRAINED_RESIZE_POLICY);
        tabellaContatti.setItems(FXCollections.observableArrayList(contacts));
    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ContactMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

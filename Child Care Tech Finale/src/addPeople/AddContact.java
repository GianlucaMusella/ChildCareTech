package addPeople;

import getterAndSetter.people.ContactGS;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddContact implements Initializable{

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TableView<ContactGS> tabellaContatti;

    @FXML
    private TableColumn<ContactGS, String> colonnaNome;

    @FXML
    private TableColumn<ContactGS, String> colonnaCognome;

    @FXML
    private TableColumn<ContactGS, String> colonnaCodiceFiscale;

    @FXML
    private Label lblStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

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

    public void addContact(ActionEvent actionEvent) throws Exception {

        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String telefono = txtTelefono.getText();

        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtCodiceFiscale.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }

                boolean success = interfaceServer.addContact(codiceFiscale, nome, cognome, telefono);

                if (success) {
                    txtNome.clear();
                    txtCognome.clear();
                    txtCodiceFiscale.clear();
                    txtTelefono.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
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

    public void menuPrincipale(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Child Care Tech");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }


}

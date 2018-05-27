package modify;

import getterAndSetter.people.ContactGS;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.rmi.RemoteException;
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

        tabellaContatti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaContatti.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscaleOld.setText(newSelection.getCodiceFiscale());
            }
        });

        tabellaContatti.getItems().clear();
    }

    public void modifyContact(ActionEvent actionEvent) throws Exception {

        String codiceFiscale = txtCodicefiscaleOld.getText();
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String telefono = txtTelefono.getText();

        if (txtCodicefiscaleOld.getText().isEmpty() || txtCodicefiscaleOld.getText().length() != 16 || txtCognome.getText().isEmpty() || txtNome.getText().isEmpty() || txtTelefono.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {

            try{
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.modifyContact(codiceFiscale, nome, cognome, telefono);
                if (success) {
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Modifica riuscita");
                    txtCodicefiscaleOld.clear();
                    txtNome.clear();
                    txtCognome.clear();
                    txtTelefono.clear();
                }
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }
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

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ContactMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Contatto");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

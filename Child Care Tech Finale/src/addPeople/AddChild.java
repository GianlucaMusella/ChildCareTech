package addPeople;

import getterAndSetter.people.ChildGS;
import getterAndSetter.people.ContactGS;
import getterAndSetter.people.DoctorGS;
import getterAndSetter.people.ParentsGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.RadioButton;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddChild implements Initializable{

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private TextField txtLuogo;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtGenitore1;

    @FXML
    private TextField txtGenitore2;

    @FXML
    private TextField txtPediatra;

    @FXML
    private TextField txtAllergia;

    @FXML
    private TextField txtIDBambino;

    @FXML
    private TextField txtContatto;

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
    private RadioButton radioMaschio;

    @FXML
    private RadioButton radioFemmina;

    @FXML
    private TableView<ParentsGS> tabellaGenitori;

    @FXML
    private TableColumn<ParentsGS, String> colonnaNome;

    @FXML
    private TableColumn<ParentsGS, String> colonnaCognome;

    @FXML
    private TableColumn<ParentsGS, String> colonnaCF;

    @FXML
    private TableView<ContactGS> tabellaContatti;

    @FXML
    private TableColumn<ContactGS, String> colonnaNomeC;

    @FXML
    private TableColumn<ContactGS, String> colonnaCognomeC;

    @FXML
    private TableColumn<ContactGS, String> colonnaCfC;

    @FXML
    private TableView<DoctorGS> tabellaPediatra;

    @FXML
    private TableColumn<DoctorGS, String> colonnaNomeP;

    @FXML
    private TableColumn<DoctorGS, String> colonnaCognomeP;

    @FXML
    private TableColumn<DoctorGS, String> colonnaCfP;

    @FXML
    private Label lblStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("idBambino"));

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCF.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        colonnaNomeC.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognomeC.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCfC.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        colonnaNomeP.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognomeP.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCfP.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaGenitori.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaGenitori.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtGenitore1.setText(newSelection.getCodiceFiscale());
            }
        });

        tabellaContatti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaContatti.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtContatto.setText(newSelection.getCodiceFiscale());
            }
        });

        tabellaPediatra.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaPediatra.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtPediatra.setText(newSelection.getCodiceFiscale());
            }
        });

        tableBambini.getItems().clear();
        tabellaGenitori.getItems().clear();
        tabellaContatti.getItems().clear();
        tabellaPediatra.getItems().clear();

    }

    public void addChild(ActionEvent actionEvent) throws Exception {

        String sesso;
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String luogo = txtLuogo.getText();
        LocalDate data = dateData.getValue();
        String genitore1 = txtGenitore1.getText();
        String genitore2 = txtGenitore2.getText();
        String pediatra = txtPediatra.getText();
        String allergie = txtAllergia.getText();
        String idBambino = txtIDBambino.getText();
        String contatto = txtContatto.getText();
        String sessoM = radioMaschio.getText();
        String sessoF = radioFemmina.getText();

        if (radioMaschio.isSelected())
            sesso = sessoM;
        else
            sesso = sessoF;

        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtCodiceFiscale.getText().isEmpty() && txtCodiceFiscale.getText().length() == 16 ||
                txtLuogo.getText().isEmpty() || txtPediatra.getText().isEmpty() || txtIDBambino.getText().isEmpty() || txtContatto.getText().isEmpty() || txtGenitore1.getText().isEmpty() ) {
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        }
        else {
            try{

                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.addChild(codiceFiscale, idBambino, nome, cognome, data, luogo, allergie, genitore1, genitore2, sesso, pediatra, contatto);

                if(success){
                    txtNome.clear();
                    txtCognome.clear();
                    txtCodiceFiscale.clear();
                    txtLuogo.clear();
                    dateData.getEditor().clear();
                    txtGenitore1.clear();
                    txtGenitore2.clear();
                    txtPediatra.clear();
                    txtAllergia.clear();
                    txtIDBambino.clear();
                    txtContatto.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }



            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    public void viewChild(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        ArrayList<ChildGS> childrenGS = interfaceServer.viewChild();

        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGS));

    }

    public void viewParents(ActionEvent actionEvent) throws Exception {
        InterfaceServer interfaceServer;

        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<ParentsGS> parents = interfaceServer.viewParents();

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(parents));

    }

    public void viewDoctor(ActionEvent actionEvent) throws Exception {
        InterfaceServer interfaceServer;

        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        ArrayList<DoctorGS> doctorGS = interfaceServer.viewDoctors();

        tabellaPediatra.setColumnResizePolicy(tabellaPediatra.CONSTRAINED_RESIZE_POLICY);
        tabellaPediatra.setItems(FXCollections.observableArrayList(doctorGS));

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


    public void inserisciGenitore(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/add/AddParents.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void inserisciContatto(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/add/AddContact.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Contatto");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ChildMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Bambino");
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

    public void inserisciPediatra(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/add/AddDoctor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Pediatra");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

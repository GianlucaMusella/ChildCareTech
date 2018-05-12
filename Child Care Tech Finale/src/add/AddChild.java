package add;

import getterAndSetter.ChildGS;
import getterAndSetter.Contact;
import getterAndSetter.Doctor;
import getterAndSetter.Parents;
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
import javafx.stage.Stage;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
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
    private TableColumn<ChildGS, String> columnCodicefiscale;

    @FXML
    private TableColumn<ChildGS, String> columnDatadinascita;

    @FXML
    private TableColumn<ChildGS, String> columnLuogodinascita;

    @FXML
    private TableColumn<ChildGS, String> columnID;

    @FXML
    private RadioButton radioMaschio;

    @FXML
    private RadioButton radioFemmina;

    @FXML
    private TableView<Parents> tabellaGenitori;

    @FXML
    private TableColumn<Parents, String> colonnaNome;

    @FXML
    private TableColumn<Parents, String> colonnaCF;

    @FXML
    private TableView<Contact> tabellaContatti;

    @FXML
    private TableColumn<Contact, String> colonnaNomeC;

    @FXML
    private TableColumn<Contact, String> colonnaCfC;

    @FXML
    private TableView<Doctor> tabellaPediatra;

    @FXML
    private TableColumn<Doctor, String> colonnaNomeP;

    @FXML
    private TableColumn<Doctor, String> colonnaCfP;


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


        if(radioMaschio.isSelected())
            sesso = sessoM;
        else
            sesso = sessoF;

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addChild(codiceFiscale, idBambino,  nome,  cognome,  data,  luogo,  allergie,  genitore1,  genitore2, sesso, pediatra, contatto);

        //se metti il cambio del label serve la try catch con remoteexception


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


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnDatadinascita.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnLuogodinascita.setCellValueFactory(new PropertyValueFactory<>("luogoDiNascita"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("idBambino"));

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCF.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        colonnaNomeC.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCfC.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        colonnaNomeP.setCellValueFactory(new PropertyValueFactory<>("nome"));
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

    public void viewChild(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<ChildGS> childrenGS = interfaceRMI.viewChild();
        ArrayList<Parents> parents = interfaceRMI.viewParents();
        ArrayList<Contact> contacts = interfaceRMI.viewContacts();
        ArrayList<Doctor> doctors = interfaceRMI.viewDoctors();

        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGS));

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(parents));

        tabellaContatti.setColumnResizePolicy(tabellaContatti.CONSTRAINED_RESIZE_POLICY);
        tabellaContatti.setItems(FXCollections.observableArrayList(contacts));

        tabellaPediatra.setColumnResizePolicy(tabellaPediatra.CONSTRAINED_RESIZE_POLICY);
        tabellaPediatra.setItems(FXCollections.observableArrayList(doctors));
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
        stage.setScene(scene);
        stage.show();

    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ChildMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void menuPrincipale(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void inserisciPediatra(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/add/AddDoctor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}

package dataEntry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.time.LocalDate;


public class AddChild {

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
    private TextField txtSesso;

    @FXML
    private TextField txtContatto;

    @FXML
    private TableView tableBambini;

    @FXML
    private TableColumn columnNome;

    @FXML
    private TableColumn columnCognome;

    @FXML
    private TableColumn columnCodiceFiscale;

    public void addChild(ActionEvent actionEvent) throws Exception {

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
        String sesso = txtSesso.getText();
        String contatto = txtContatto.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addChild(codiceFiscale, idBambino,  nome,  cognome,  data,  luogo,  allergie,  genitore1,  genitore2, sesso, pediatra, contatto);

        //se metti il cambio del label serve la try catch con remoteexception


        txtNome.clear();
        txtCognome.clear();
        txtCodiceFiscale.clear();
        txtLuogo.clear();

        txtGenitore1.clear();
        txtGenitore2.clear();
        txtPediatra.clear();
        txtAllergia.clear();
        txtIDBambino.clear();
        txtSesso.clear();
        txtContatto.clear();

    }

    public void inserisciGenitore(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/dataEntry/AddParents.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void inserisciContatto(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/dataEntry/AddContact.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/menu/InsertPeople.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void menuPrincipale(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}

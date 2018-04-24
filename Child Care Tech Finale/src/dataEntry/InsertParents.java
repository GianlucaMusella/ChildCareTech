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

public class InsertParents {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtLuogo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtSesso;

    @FXML
    private TableView tableBambini;

    @FXML
    private TableColumn columnNome;

    @FXML
    private TableColumn columnCognome;

    @FXML
    private TableColumn columnCodiceFiscale;

    public void addParents(ActionEvent actionEvent) throws Exception {

        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String luogo = txtLuogo.getText();
        LocalDate data = dateData.getValue();
        String sesso = txtSesso.getText();
        String telefono = txtTelefono.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addParents(codiceFiscale,  nome,  cognome,  data,  luogo, telefono, sesso);

        //se metti il cambio del label serve la try catch con remoteexception


        txtNome.clear();
        txtCognome.clear();
        txtCodiceFiscale.clear();
        txtLuogo.clear();
        txtTelefono.clear();
        txtSesso.clear();

    }


    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/menu/InsertContact.fxml"));
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

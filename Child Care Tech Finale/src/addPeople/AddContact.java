package addPeople;

import getterAndSetter.people.ContactGS;
import getterAndSetter.people.ParentsGS;
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
import main.Singleton;
import serverRMI.InterfaceRMI;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaContatti.getItems().clear();
    }

    public void viewContact(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<ContactGS> contactGS = interfaceRMI.viewContacts();

        tabellaContatti.setColumnResizePolicy(tabellaContatti.CONSTRAINED_RESIZE_POLICY);
        tabellaContatti.setItems(FXCollections.observableArrayList(contactGS));
    }

    public void addContact(ActionEvent actionEvent) throws Exception {

        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String telefono = txtTelefono.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addContact(codiceFiscale, nome, cognome, telefono);

        //se metti il cambio del label serve la try catch con remoteexception
        /*if(success == true)
            JOptionPane.showMessageDialog(null, "Inserimento avvenuto con successo", "Risultato", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Inserimento fallito", "Risultato", JOptionPane.INFORMATION_MESSAGE);
*/
        txtNome.clear();
        txtCognome.clear();
        txtCodiceFiscale.clear();
        txtTelefono.clear();


    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ContactMenu.fxml"));
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


}

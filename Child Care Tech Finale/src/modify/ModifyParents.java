package modify;

import getterAndSetter.people.ParentsGS;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;
import javafx.event.ActionEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyParents implements Initializable {

    @FXML
    private TextField txtCodicefiscaleOld;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtLuogo;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TableView<ParentsGS> tabellaGenitori;

    @FXML
    private TableColumn<ParentsGS, String> colonnaNome;

    @FXML
    private TableColumn<ParentsGS, String> colonnaCognome;

    @FXML
    private TableColumn<ParentsGS, String> colonnaCodiceFiscale;

    @FXML
    private Label lblStatus;


    public void modifyParents (ActionEvent actionEvent) throws Exception {
        if (txtCodicefiscaleOld.getText().isEmpty() || txtCodicefiscaleOld.getText().length() != 16 || txtCognome.getText().isEmpty() || txtNome.getText().isEmpty() || txtTelefono.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String codiceFiscale = txtCodicefiscaleOld.getText();
            String nome = txtNome.getText();
            String cognome = txtCognome.getText();
            String luogo = txtLuogo.getText();
            LocalDate data = dateData.getValue();
            String telefono = txtTelefono.getText();
            InterfaceRMI interfaceRMI;
            if (Controller.selection.equals("RMI")) {
                interfaceRMI = Singleton.getInstance().rmiLookup();
            } else {
                interfaceRMI = Singleton.getInstance().methodSocket();
            }
            interfaceRMI.modifyParents(codiceFiscale, nome, cognome, luogo, data, telefono);

            txtCodicefiscaleOld.clear();
            txtNome.clear();
            txtCognome.clear();
            txtLuogo.clear();
            dateData.getEditor().clear();
            txtTelefono.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaGenitori.getItems().clear();
    }

    public void viewParents(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        ArrayList<ParentsGS> parents = interfaceRMI.viewParents();

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(parents));
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ParentsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


}

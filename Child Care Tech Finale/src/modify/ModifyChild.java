package modify;

import getterAndSetter.people.ChildGS;
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
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ModifyChild implements Initializable{

    @FXML
    private TextField txtCodicefiscaleOld;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtLuogo;

    @FXML
    private TextField idBambino;

    @FXML
    private DatePicker dateData;

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
    private Label lblStatus;

    public void modifyClient (ActionEvent actionEvent) throws Exception {
        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtCodicefiscaleOld.getText().isEmpty() || txtCodicefiscaleOld.getText().length() != 16 ||
                txtLuogo.getText().isEmpty() || idBambino.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String nome = txtNome.getText();
            String cognome = txtCognome.getText();
            String codiceFiscale = txtCodicefiscaleOld.getText();
            String luogo = txtLuogo.getText();
            LocalDate data = dateData.getValue();
            String id = idBambino.getText();
            InterfaceRMI interfaceRMI;
            if (Controller.selection.equals("RMI")) {
                interfaceRMI = Singleton.getInstance().rmiLookup();
            } else {
                interfaceRMI = Singleton.getInstance().methodSocket();
            }
            interfaceRMI.modifyChild(codiceFiscale, nome, cognome, luogo, data, id);

            txtCodicefiscaleOld.clear();
            txtCognome.clear();
            txtNome.clear();
            txtLuogo.clear();
            idBambino.clear();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("idBambino"));

        tableBambini.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableBambini.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscaleOld.setText(newSelection.getCodiceFiscale());
            }
        });

        tableBambini.getItems().clear();
    }

    public void viewChild(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        ArrayList<ChildGS> childrenGS = interfaceRMI.viewChild();

        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGS));

    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ChildMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}

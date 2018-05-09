package searchModifyDelete;

import dataEntry.Parents;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;
import javafx.event.ActionEvent;

import java.net.URL;
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
    private TableView<Parents> tabellaGenitori;

    @FXML
    private TableColumn<Parents, String> colonnaNome;

    @FXML
    private TableColumn<Parents, String> colonnaCodiceFiscale;

    public void modifyParents (ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.modifyParents(txtCodicefiscaleOld.getText(), txtNome.getText(), txtCognome.getText(), txtLuogo.getText(), dateData.getValue(), txtTelefono.getText());

        txtCodicefiscaleOld.clear();
        txtNome.clear();
        txtCognome.clear();
        txtLuogo.clear();
        dateData.getEditor().clear();
        txtTelefono.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaGenitori.getItems().clear();
    }

    public void viewParents(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<Parents> parents = interfaceRMI.viewParents();

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(parents));
    }

}

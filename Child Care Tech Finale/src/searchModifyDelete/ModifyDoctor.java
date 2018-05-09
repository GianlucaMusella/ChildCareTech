package searchModifyDelete;

import dataEntry.Doctor;
import dataEntry.Parents;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import javax.print.Doc;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyDoctor implements Initializable {

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
    private TableView<Doctor> tabellaGenitori;

    @FXML
    private TableColumn<Doctor, String> colonnaNome;

    @FXML
    private TableColumn<Doctor, String> colonnaCodiceFiscale;

    public void modifyDoctor (ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.modifyDoctor(txtCodicefiscaleOld.getText(), txtNome.getText(), txtCognome.getText(), txtLuogo.getText(), dateData.getValue());

        txtCodicefiscaleOld.clear();
        txtNome.clear();
        txtCognome.clear();
        txtLuogo.clear();
        dateData.getEditor().clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaGenitori.getItems().clear();
    }

    public void viewDoctor(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<Doctor> doctors = interfaceRMI.viewDoctors();

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(doctors));
    }

}

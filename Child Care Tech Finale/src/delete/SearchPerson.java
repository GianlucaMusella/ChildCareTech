package delete;

import dataEntry.ChildGS;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchPerson implements Initializable {

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

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
    private Button buttonSearch;

    public SearchPerson(Button buttonSearch) {
        this.buttonSearch = buttonSearch;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnDatadinascita.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnLuogodinascita.setCellValueFactory(new PropertyValueFactory<>("luogoDiNascita"));

        tableBambini.getItems().clear();

    }
    public void searchChild() throws Exception {
        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<ChildGS> childrenGsALL = interfaceRMI.searchCALL();
        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGsALL));

    }
    public void searchChild(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        // ObservableList<ChildGS> valuess = interfaceRMI.searchC(txtNome.getText(), txtCognome.getText(), txtCodicefiscale.getText());

        ArrayList<ChildGS> childrenGS = interfaceRMI.searchC(txtNome.getText(), txtCognome.getText(), txtCodicefiscale.getText());
        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGS));

    }
}
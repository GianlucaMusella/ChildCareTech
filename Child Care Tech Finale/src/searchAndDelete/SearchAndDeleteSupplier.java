package searchAndDelete;

import getterAndSetter.people.SupplierGS;
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
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchAndDeleteSupplier implements Initializable{

    @FXML
    private TextField txtAzienda;

    @FXML
    private TextField txtFornitura;

    @FXML
    private TextField txtPartitaIva;

    @FXML
    private TableView<SupplierGS> tabellaAzienda;

    @FXML
    private TableColumn<SupplierGS, String> colonnaAzienda;

    @FXML
    private TableColumn<SupplierGS, String> colonnaFornitura;

    @FXML
    private TableColumn<SupplierGS, String> colonnaPartitaIva;

    @FXML
    private Button show;

    @FXML
    private Label lblStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaAzienda.setCellValueFactory(new PropertyValueFactory<>("azienda"));
        colonnaFornitura.setCellValueFactory(new PropertyValueFactory<>("fornitura"));
        colonnaPartitaIva.setCellValueFactory(new PropertyValueFactory<>("partitaIva"));

        tabellaAzienda.getItems().clear();

        show.fire();
    }

    public void viewSupplier(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<SupplierGS> supplierGS = interfaceServer.viewSupplier();

        tabellaAzienda.setColumnResizePolicy(tabellaAzienda.CONSTRAINED_RESIZE_POLICY);
        tabellaAzienda.setItems(FXCollections.observableArrayList(supplierGS));
    }


    public void searchSupplier(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<SupplierGS> supplierGS = interfaceServer.searchSupplier(txtAzienda.getText(), txtFornitura.getText(), txtPartitaIva.getText());

        tabellaAzienda.setColumnResizePolicy(tabellaAzienda.CONSTRAINED_RESIZE_POLICY);
        tabellaAzienda.setItems(FXCollections.observableArrayList(supplierGS));
    }


    public void deleteSupplier (ActionEvent actionEvent) throws Exception {
        if (tabellaAzienda.getSelectionModel().isEmpty()) {
            lblStatus.setText("ERRORE: nessun elemento selezionato");
        } else {
            SupplierGS deletableSupplier = tabellaAzienda.getSelectionModel().getSelectedItem();
            int index = tabellaAzienda.getSelectionModel().getSelectedIndex();
            String azienda = deletableSupplier.getAzienda();

            System.out.println(azienda); // Ho messo questo per capire se prende il codice fiscale giusto

            InterfaceServer interfaceServer;
            if (Controller.selection.equals("RMI")) {
                interfaceServer = Singleton.getInstance().rmiLookup();
            } else {
                interfaceServer = Singleton.getInstance().methodSocket();
            }
            boolean success = interfaceServer.deleteSupplier(azienda);
            if (success) {
                tabellaAzienda.getItems().remove(index);
            }
        }
    }
    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/SupplierMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Fornitori");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent ev) throws Exception {
        ((Node) ev.getSource()).getScene().getWindow().hide();
        Controller controller = new Controller();
        Stage stage = new Stage();
        stage.setTitle("Child Care Tech");
        stage.setResizable(false);
        controller.initialize(stage);
    }

}

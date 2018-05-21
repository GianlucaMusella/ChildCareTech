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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;

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




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaAzienda.setCellValueFactory(new PropertyValueFactory<>("azienda"));
        colonnaFornitura.setCellValueFactory(new PropertyValueFactory<>("fornitura"));
        colonnaPartitaIva.setCellValueFactory(new PropertyValueFactory<>("partitaIva"));

        tabellaAzienda.getItems().clear();

    }

    public void viewSupplier(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        ArrayList<SupplierGS> supplierGS = interfaceRMI.viewSupplier();

        tabellaAzienda.setColumnResizePolicy(tabellaAzienda.CONSTRAINED_RESIZE_POLICY);
        tabellaAzienda.setItems(FXCollections.observableArrayList(supplierGS));
    }


    public void searchSupplier(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        ArrayList<SupplierGS> supplierGS = interfaceRMI.searchSupplier(txtAzienda.getText(), txtFornitura.getText(), txtPartitaIva.getText());

        tabellaAzienda.setColumnResizePolicy(tabellaAzienda.CONSTRAINED_RESIZE_POLICY);
        tabellaAzienda.setItems(FXCollections.observableArrayList(supplierGS));
    }


    public void deleteSupplier (ActionEvent actionEvent) throws Exception {

        SupplierGS deletableSupplier = tabellaAzienda.getSelectionModel().getSelectedItem();
        String azienda = deletableSupplier.getAzienda();

        System.out.println(azienda); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        interfaceRMI.deleteSupplier(azienda);


    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/SupplierMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent ev) throws Exception {
        ((Node) ev.getSource()).getScene().getWindow().hide();
        Controller controller = new Controller();
        Stage stage = new Stage();
        controller.initialize(stage);
    }

}

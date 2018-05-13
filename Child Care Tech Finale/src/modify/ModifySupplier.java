package modify;

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
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifySupplier implements Initializable{

    @FXML
    private TextField txtAzienda;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtFornitura;

    @FXML
    private TextField txtPartitaIva;

    @FXML
    private TableView<SupplierGS> tabellaFornitori;

    @FXML
    private TableColumn<SupplierGS, String> colonnaAzienda;

    @FXML
    private TableColumn<SupplierGS, String> colonnaFornitura;

    @FXML
    private TableColumn<SupplierGS, String> colonnaPartitaIva;

    public void modifySupplier(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.modifySupplier(txtAzienda.getText(), txtNome.getText(), txtCognome.getText(), txtFornitura.getText(), txtPartitaIva.getText());

        txtAzienda.clear();
        txtNome.clear();
        txtCognome.clear();
        txtFornitura.clear();
        txtPartitaIva.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colonnaAzienda.setCellValueFactory(new PropertyValueFactory<>("azienda"));
        colonnaFornitura.setCellValueFactory(new PropertyValueFactory<>("fornitura"));
        colonnaPartitaIva.setCellValueFactory(new PropertyValueFactory<>("partitaIva"));

        tabellaFornitori.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaFornitori.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtAzienda.setText(newSelection.getAzienda());
            }
        });

        tabellaFornitori.getItems().clear();
    }

    public void viewSupplier(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<SupplierGS> supplierGS = interfaceRMI.viewSupplier();

        tabellaFornitori.setColumnResizePolicy(tabellaFornitori.CONSTRAINED_RESIZE_POLICY);
        tabellaFornitori.setItems(FXCollections.observableArrayList(supplierGS));
    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/SupplierMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

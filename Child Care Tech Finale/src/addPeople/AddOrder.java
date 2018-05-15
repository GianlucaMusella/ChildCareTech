package addPeople;

import getterAndSetter.people.SupplierGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddOrder implements Initializable{

    @FXML
    private TextField txtAzienda;

    @FXML
    private TextField txtOrdine;

    @FXML
    private TextField txtQuantità;

    @FXML
    private TableView<SupplierGS> tabellaFornitori;

    @FXML
    private TableColumn<SupplierGS, String> colonnaAzienda;

    @FXML
    private TableColumn<SupplierGS, String> colonnaPartitaIVA;

    @FXML
    private TableColumn<SupplierGS, String> colonnaFornitura;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colonnaAzienda.setCellValueFactory(new PropertyValueFactory<>("azienda"));
        colonnaFornitura.setCellValueFactory(new PropertyValueFactory<>("fornitura"));
        colonnaPartitaIVA.setCellValueFactory(new PropertyValueFactory<>("partitaIva"));

        tabellaFornitori.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaFornitori.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtAzienda.setText(newSelection.getAzienda());
            }
        });

        tabellaFornitori.getItems().clear();
    }

    public void viewSupplier(ActionEvent actionEvent) throws Exception{

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<SupplierGS> supplierGS = interfaceRMI.viewSupplier();

        tabellaFornitori.setColumnResizePolicy(tabellaFornitori.CONSTRAINED_RESIZE_POLICY);
        tabellaFornitori.setItems(FXCollections.observableArrayList(supplierGS));
    }

    public void addOrder(ActionEvent actionEvent) throws Exception{

        String azienda = txtAzienda.getText();
        String ordini = txtOrdine.getText();
        String quantità = txtQuantità.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addOrder(azienda, ordini, quantità);

        txtAzienda.clear();
        txtOrdine.clear();
        txtQuantità.clear();

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

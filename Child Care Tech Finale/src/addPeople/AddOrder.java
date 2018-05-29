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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

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

    @FXML
    private Label lblStatus;

    @FXML
    private Button show;

    private InterfaceServer interfaceServer;

    public AddOrder(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaAzienda.setCellValueFactory(new PropertyValueFactory<>("azienda"));
        colonnaPartitaIVA.setCellValueFactory(new PropertyValueFactory<>("partitaIva"));
        colonnaFornitura.setCellValueFactory(new PropertyValueFactory<>("fornitura"));


        tabellaFornitori.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tabellaFornitori.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtAzienda.setText(newSelection.getAzienda());
                txtOrdine.setText(newSelection.getFornitura());
            }
        });

        tabellaFornitori.getItems().clear();
        show.fire();
    }

    public void viewSupplier(ActionEvent actionEvent) throws Exception{

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<SupplierGS> supplierGS = interfaceServer.viewSupplier();

        tabellaFornitori.setColumnResizePolicy(tabellaFornitori.CONSTRAINED_RESIZE_POLICY);
        tabellaFornitori.setItems(FXCollections.observableArrayList(supplierGS));
    }

    public void addOrder(ActionEvent actionEvent) throws Exception {

        String azienda = txtAzienda.getText();
        String ordini = txtOrdine.getText();
        String quantità = txtQuantità.getText();

        if (txtAzienda.getText().isEmpty() || txtOrdine.getText().isEmpty() || txtQuantità.getText().isEmpty()){
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        }else {

            try {

                boolean success = interfaceServer.addOrder(azienda, ordini, quantità);

                if (!success) {
                    txtAzienda.clear();
                    txtOrdine.clear();
                    txtQuantità.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Ordine registrato");
                    show.fire();
                }
            } catch (Exception e){
                e.printStackTrace();
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
}

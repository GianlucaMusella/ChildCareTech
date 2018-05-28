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


public class AddSupplier implements Initializable{

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtAzienda;

    @FXML
    private TextField txtFornitura;

    @FXML
    private TextField txtPiva;

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

    public AddSupplier(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaAzienda.setCellValueFactory(new PropertyValueFactory<>("azienda"));
        colonnaFornitura.setCellValueFactory(new PropertyValueFactory<>("fornitura"));
        colonnaPartitaIVA.setCellValueFactory(new PropertyValueFactory<>("partitaIva"));

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

    public void addSupplier(ActionEvent actionEvent) throws Exception {

        String name = txtName.getText();
        String surname = txtSurname.getText();
        String azienda = txtAzienda.getText();
        String fornitura = txtFornitura.getText();
        String partitaIva = txtPiva.getText();

        if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtPiva.getText().isEmpty() && txtPiva.getText().length() == 11 || txtAzienda.getText().isEmpty()
                || txtFornitura.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            try {

                boolean success = interfaceServer.addSupplier(name, surname, azienda, fornitura, partitaIva);
                if (success) {
                    txtName.clear();
                    txtSurname.clear();
                    txtAzienda.clear();
                    txtFornitura.clear();
                    txtPiva.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                    show.fire();
                }
            } catch (Exception e ) {
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

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.rmi.RemoteException;
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

    @FXML
    private Label lblStatus;

    public void modifySupplier(ActionEvent actionEvent) throws Exception {

        String azienda = txtAzienda.getText();
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String fornitura = txtFornitura.getText();
        String partitaIVA = txtPartitaIva.getText();

        if (txtAzienda.getText().isEmpty() || txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtFornitura.getText().isEmpty()
                || txtPartitaIva.getText().isEmpty() || txtPartitaIva.getText().length() != 11)
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.modifySupplier(azienda, nome, cognome, fornitura, partitaIVA);
                if (success) {
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Modifica riuscita");
                    txtAzienda.clear();
                    txtNome.clear();
                    txtCognome.clear();
                    txtFornitura.clear();
                    txtPartitaIva.clear();
                }
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
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

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/SupplierMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Fornitore");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

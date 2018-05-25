package menuFood;

import getterAndSetter.food.BambiniAllergici;
import getterAndSetter.food.FirstDishGS;
import interfaces.InterfaceServer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Controller;
import main.Singleton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckAllergy implements Initializable {
    @FXML
    private TextField txtNomeMenu;

    @FXML
    private TableView<BambiniAllergici> tabellaCheck;

    @FXML
    private TableColumn<BambiniAllergici, String> nome;

    @FXML
    private TableColumn<BambiniAllergici, String> cognome;

    @FXML
    private TableColumn<BambiniAllergici, String> allergene;

    @FXML
    private TableColumn<BambiniAllergici, String> primo;

    @FXML
    private TableColumn<BambiniAllergici, String> secondo;

    @FXML
    private TableColumn<BambiniAllergici, String> menu;

    @FXML
    private Button check;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nome.setCellValueFactory(new PropertyValueFactory<>("nomeBambino"));
        cognome.setCellValueFactory(new PropertyValueFactory<>("cognomeBambino"));
        allergene.setCellValueFactory(new PropertyValueFactory<>("nomeAllergene"));
        primo.setCellValueFactory(new PropertyValueFactory<>("nomePrimo"));
        secondo.setCellValueFactory(new PropertyValueFactory<>("nomeSecondo"));
        menu.setCellValueFactory(new PropertyValueFactory<>("nomeMenu"));


        tabellaCheck.getItems().clear();

//        check.fire();
    }

    public void viewCheck(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<BambiniAllergici> bambiniAllergici = interfaceServer.viewCheck(txtNomeMenu.getText());

        tabellaCheck.setColumnResizePolicy(tabellaCheck.CONSTRAINED_RESIZE_POLICY);
        tabellaCheck.setItems(FXCollections.observableArrayList(bambiniAllergici));
    }
}

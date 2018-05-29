package menuFood;

import getterAndSetter.food.MenuGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuTable implements Initializable{

    @FXML
    private TableView<MenuGS> tabellaMenu;

    @FXML
    private TableColumn<MenuGS, String> colonnaNome;

    @FXML
    private TableColumn<MenuGS, String> colonnaPrimi;

    @FXML
    private TableColumn<MenuGS, String> colonnaSecondi;

    @FXML
    private TableColumn<MenuGS, String> colonnaGiorno;

    @FXML
    private Label lblStatus;

    @FXML
    private Button show;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nomeMenu"));
        colonnaPrimi.setCellValueFactory(new PropertyValueFactory<>("nomePrimo"));
        colonnaSecondi.setCellValueFactory(new PropertyValueFactory<>("nomeSecondo"));
        colonnaGiorno.setCellValueFactory(new PropertyValueFactory<>("giorno"));

        tabellaMenu.getItems().clear();

        show.fire();
    }

    public void viewMenu(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<MenuGS> menuGS = interfaceServer.viewMenu();

        tabellaMenu.setColumnResizePolicy(tabellaMenu.CONSTRAINED_RESIZE_POLICY);
        tabellaMenu.setItems(FXCollections.observableArrayList(menuGS));

    }

    public void deleteMenu(ActionEvent actionEvent) throws Exception {

        if (tabellaMenu.getSelectionModel().isEmpty()) {
            lblStatus.setText("ERRORE: nessun elemento selezionato");
        }
        else {
            MenuGS deletableMenu = tabellaMenu.getSelectionModel().getSelectedItem();
            String nomeMenu = deletableMenu.getNomeMenu();

            System.out.println(nomeMenu); // Ho messo questo per capire se prende il codice fiscale giusto

            InterfaceServer interfaceServer;
            if (Controller.selection.equals("RMI")) {
                interfaceServer = Singleton.getInstance().rmiLookup();
            } else {
                interfaceServer = Singleton.getInstance().methodSocket();
            }
            interfaceServer.deleteMenu(nomeMenu);

            show.fire();
        }
    }



    public void inserisciMenu(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/AddMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Menù Mensa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Child Care Tech");
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

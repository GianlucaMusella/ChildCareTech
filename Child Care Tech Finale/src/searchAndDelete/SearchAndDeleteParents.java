package searchAndDelete;

import getterAndSetter.people.ParentsGS;
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
import interfaces.InterfaceServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchAndDeleteParents implements Initializable{

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtNome;

    @FXML
    private TableView<ParentsGS> tabellaGenitori;

    @FXML
    private TableColumn<ParentsGS, String> columnNome;

    @FXML
    private TableColumn<ParentsGS, String> columnCognome;

    @FXML
    private TableColumn<ParentsGS, String> columnCodicefiscale;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));

        tabellaGenitori.getItems().clear();

    }

    public void viewParents(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<ParentsGS> parents = interfaceServer.viewParents();

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(parents));
    }


    public void searchParents(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<ParentsGS> parents = interfaceServer.searchParents(txtNome.getText(), txtCodicefiscale.getText());

        tabellaGenitori.setColumnResizePolicy(tabellaGenitori.CONSTRAINED_RESIZE_POLICY);
        tabellaGenitori.setItems(FXCollections.observableArrayList(parents));
    }


    public void deleteParents (ActionEvent actionEvent) throws Exception {

        ParentsGS deletableParentsGS = tabellaGenitori.getSelectionModel().getSelectedItem();
        int index = tabellaGenitori.getSelectionModel().getSelectedIndex();
        String codiceFiscale = deletableParentsGS.getCodiceFiscale();

        System.out.println(codiceFiscale); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        interfaceServer.deleteParents(codiceFiscale);
        tabellaGenitori.getItems().remove(index);

    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/ParentsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Genitori");
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

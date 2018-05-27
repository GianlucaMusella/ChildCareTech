package menuFood;

import getterAndSetter.food.FirstDishGS;
import getterAndSetter.food.SecondDishGS;
import getterAndSetter.food.SideDishGS;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddMenu implements Initializable{

    @FXML
    private TextField nomeMenu;

    @FXML
    private TextField primoPiatto;

    @FXML
    private TextField secondoPiatto;

    @FXML
    private TextField txtContorno;

    @FXML
    private DatePicker txtGiorno;

    @FXML
    private TableView<FirstDishGS> tabellaPrimi;

    @FXML
    private TableColumn<FirstDishGS, String> colonnaPrimi;

    @FXML
    private TableColumn<FirstDishGS, String> colonnaAllergeni;

    @FXML
    private TableView<SecondDishGS> tabellaSecondi;

    @FXML
    private TableColumn<SecondDishGS, String> colonnaSecondi;

    @FXML
    private TableColumn<SecondDishGS, String> colonnaAllergeniSecondi;

    @FXML
    private TableView<SideDishGS> tabellaContorni;

    @FXML
    private TableColumn<SideDishGS, String> colonnaContorni;

    @FXML
    private TableColumn<SideDishGS, String> colonnaAllergieContorni;

    @FXML
    private Button show;

    @FXML
    private Label lblStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaPrimi.setCellValueFactory(new PropertyValueFactory<>("nomePrimo"));
        colonnaAllergeni.setCellValueFactory(new PropertyValueFactory<>("allergene"));

        colonnaSecondi.setCellValueFactory(new PropertyValueFactory<>("nomeSecondo"));
        colonnaAllergeniSecondi.setCellValueFactory(new PropertyValueFactory<>("allergene"));

        colonnaContorni.setCellValueFactory(new PropertyValueFactory<>("nomeContorno"));
        colonnaAllergieContorni.setCellValueFactory(new PropertyValueFactory<>("allergene"));

        tabellaPrimi.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaPrimi.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                primoPiatto.setText(newSelection.getNomePrimo());
            }
        });

        tabellaSecondi.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaSecondi.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                secondoPiatto.setText(newSelection.getNomeSecondo());
            }
        });

        tabellaContorni.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaContorni.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtContorno.setText(newSelection.getNomeContorno());
            }
        });

        tabellaPrimi.getItems().clear();
        tabellaSecondi.getItems().clear();
        tabellaContorni.getItems().clear();

        show.fire();

    }

    public void viewFirst(ActionEvent actionEvent) throws Exception{

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        ArrayList<FirstDishGS> firstDishGS = interfaceServer.viewFirst();

        tabellaPrimi.setColumnResizePolicy(tabellaPrimi.CONSTRAINED_RESIZE_POLICY);
        tabellaPrimi.setItems(FXCollections.observableArrayList(firstDishGS));


    }

    public void second(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        ArrayList<SecondDishGS> secondDishGS = interfaceServer.viewSecond();
        tabellaSecondi.setColumnResizePolicy(tabellaSecondi.CONSTRAINED_RESIZE_POLICY);
        tabellaSecondi.setItems(FXCollections.observableArrayList(secondDishGS));

    }

    public void side(ActionEvent actionEvent) throws Exception {

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        ArrayList<SideDishGS> sideDishGS = interfaceServer.viewSide();
        tabellaContorni.setColumnResizePolicy(tabellaSecondi.CONSTRAINED_RESIZE_POLICY);
        tabellaContorni.setItems(FXCollections.observableArrayList(sideDishGS));

    }

    public void threeButton(ActionEvent actionEvent) throws Exception {

        viewFirst(actionEvent);
        second(actionEvent);
        side(actionEvent);
    }

    public void addMenu(ActionEvent actionEvent) throws Exception{
        if (nomeMenu.getText().isEmpty() || primoPiatto.getText().isEmpty() || secondoPiatto.getText().isEmpty() || txtContorno.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String nome = nomeMenu.getText();
            String primo = primoPiatto.getText();
            String secondo = secondoPiatto.getText();
            String contorno = txtContorno.getText();
            LocalDate giorno = txtGiorno.getValue();
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.addMenu(nome, primo, secondo, contorno, giorno);
                if (success) {
                    nomeMenu.clear();
                    primoPiatto.clear();
                    secondoPiatto.clear();
                    txtGiorno.getEditor().clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    public void check(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/menuFood/CheckAllergy.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Menù Mensa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void addFirstDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/AddFirstDish.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Primo Piatto");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void addSecondDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/AddSecondDish.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Secondo Piatto");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void addSideDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/AddSideDish.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inserisci Contorno");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/MenuTable.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Mensa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }



}

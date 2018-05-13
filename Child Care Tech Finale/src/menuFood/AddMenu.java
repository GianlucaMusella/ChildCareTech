package menuFood;

import getterAndSetter.food.AllergyPeopleGS;
import getterAndSetter.food.FirstDishGS;
import getterAndSetter.food.SecondDishGS;
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
    private TableView<AllergyPeopleGS> tabellaAllergie;

    @FXML
    private TableColumn<AllergyPeopleGS, String> allergieBambini;

    @FXML
    private TableColumn<AllergyPeopleGS, String> allergiePersonale;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaPrimi.setCellValueFactory(new PropertyValueFactory<>("nomePrimo"));
        colonnaAllergeni.setCellValueFactory(new PropertyValueFactory<>("allergene"));

        colonnaSecondi.setCellValueFactory(new PropertyValueFactory<>("nomeSecondo"));
        colonnaAllergeniSecondi.setCellValueFactory(new PropertyValueFactory<>("allergene"));

        allergieBambini.setCellValueFactory(new PropertyValueFactory<>("allergieBambini"));
        allergiePersonale.setCellValueFactory(new PropertyValueFactory<>("allergiePersonale"));

        tabellaPrimi.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tabellaPrimi.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                primoPiatto.setText(newSelection.getNomePrimo());
            }
        });

        tabellaSecondi.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                secondoPiatto.setText(newSelection.getNomeSecondo());
            }
        });

        tabellaPrimi.getItems().clear();
        tabellaSecondi.getItems().clear();
        tabellaAllergie.getItems().clear();

    }

    public void viewFirstAndSecond(ActionEvent actionEvent) throws Exception{

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<FirstDishGS> firstDishGS = interfaceRMI.viewFirst();
        ArrayList<SecondDishGS> secondDishGS = interfaceRMI.viewSecond();
        ArrayList<AllergyPeopleGS> allergyPeopleGS = interfaceRMI.viewAllergy();

        tabellaAllergie.setColumnResizePolicy(tabellaAllergie.CONSTRAINED_RESIZE_POLICY);
        tabellaAllergie.setItems(FXCollections.observableArrayList(allergyPeopleGS));

        tabellaPrimi.setColumnResizePolicy(tabellaPrimi.CONSTRAINED_RESIZE_POLICY);
        tabellaPrimi.setItems(FXCollections.observableArrayList(firstDishGS));

        tabellaSecondi.setColumnResizePolicy(tabellaSecondi.CONSTRAINED_RESIZE_POLICY);
        tabellaSecondi.setItems(FXCollections.observableArrayList(secondDishGS));
    }


    public void addMenu(ActionEvent actionEvent) throws Exception{

        String nome = nomeMenu.getText();
        String primo = primoPiatto.getText();
        String secondo = secondoPiatto.getText();
        LocalDate giorno = txtGiorno.getValue();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addMenu(nome, primo, secondo, giorno);

        nomeMenu.clear();
        primoPiatto.clear();
        secondoPiatto.clear();
        txtGiorno.getEditor().clear();


    }

    public void addFirstDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/AddFirstDish.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void addSecondDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/AddSecondDish.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/MenuTable.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }



}

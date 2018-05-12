package menuFood;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
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
    private TableView<FirstDishesGS> tabellaPrimi;

    @FXML
    private TableColumn<FirstDishesGS, String> colonnaPrimi;

    @FXML
    private TableColumn<FirstDishesGS, String> colonnaAllergeni;

    @FXML
    private TableView<SecondDishesGS> tabellaSecondi;

    @FXML
    private TableColumn<SecondDishesGS, String> colonnaSecondi;

    @FXML
    private TableColumn<SecondDishesGS, String> colonnaAllergeniSecondi;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colonnaPrimi.setCellValueFactory(new PropertyValueFactory<>("nomePrimo"));
        colonnaAllergeni.setCellValueFactory(new PropertyValueFactory<>("allergene"));

        colonnaSecondi.setCellValueFactory(new PropertyValueFactory<>("nomeSecondo"));
        colonnaAllergeniSecondi.setCellValueFactory(new PropertyValueFactory<>("allergene"));

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
    }

    public void viewFirstAndSecond(ActionEvent actionEvent) throws Exception{

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<FirstDishesGS>  firstDishesGS = interfaceRMI.viewFirst();
        ArrayList<SecondDishesGS> secondDishesGS = interfaceRMI.viewSecond();

        tabellaPrimi.setColumnResizePolicy(tabellaPrimi.CONSTRAINED_RESIZE_POLICY);
        tabellaPrimi.setItems(FXCollections.observableArrayList(firstDishesGS));

        tabellaSecondi.setColumnResizePolicy(tabellaSecondi.CONSTRAINED_RESIZE_POLICY);
        tabellaSecondi.setItems(FXCollections.observableArrayList(secondDishesGS));
    }


    public void addMenu(ActionEvent actionEvent) throws Exception{

        String nome = nomeMenu.getText();
        String primo = primoPiatto.getText();
        String secondo = secondoPiatto.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addMenu(nome, primo, secondo);

        nomeMenu.clear();
        primoPiatto.clear();
        secondoPiatto.clear();

    }

    public void addFirstDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/menuFood/InsertFirstDishes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void addSecondDish(ActionEvent actionEvent) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/menuFood/InsertSecondDishes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/menuFood/MenuTable.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }



}

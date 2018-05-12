package menuFood;


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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import loginScreen.Controller;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllergyTable implements Initializable{

    @FXML
    private TableView<AllergyGS> tabellaAllergie;

    @FXML
    private TableColumn<AllergyGS, String> allergieBambini;

    @FXML
    private TableColumn<AllergyGS, String> allergiePersonale;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allergieBambini.setCellValueFactory(new PropertyValueFactory<>("allergieBambini"));
        allergiePersonale.setCellValueFactory(new PropertyValueFactory<>("allergiePersonale"));

        tabellaAllergie.getItems().clear();
    }

    public void viewMenu(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<AllergyGS> allergyGS = interfaceRMI.viewAlletgy();


        tabellaAllergie.setColumnResizePolicy(tabellaAllergie.CONSTRAINED_RESIZE_POLICY);
        tabellaAllergie.setItems(FXCollections.observableArrayList(allergyGS));

    }


    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent ev) throws Exception {
        ((Node) ev.getSource()).getScene().getWindow().hide();
        Controller controller = new Controller();
        Stage stage = new Stage();
        controller.initialize(stage);
    }



}

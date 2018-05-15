package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Controller;


public class Choice{

    public void inserisciPersona(ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/PeopleMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void gestisciGita(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/tripHomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void inserisciMenu(ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menuFood/MenuTable.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void inserisciFornitore(ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/SupplierMenu.fxml"));
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

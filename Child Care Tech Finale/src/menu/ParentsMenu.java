package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loginScreen.Controller;

public class ParentsMenu {

    public void inserisciGenitore(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/dataEntry/AddParents.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void cercaGenitore(ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/searchModifyDelete/SearchAndDeleteParents.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void modificaGenitore(ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/searchModifyDelete/ModifyParents.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/menu/InsertPerson.fxml"));
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
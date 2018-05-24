package trip;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Controller;


public class TripMenu {

    public void organizeTrip(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripOrganize.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Organizza Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void managementTrip(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripManagement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Gestisci Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void pullman (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/Pullman.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Pullman");
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

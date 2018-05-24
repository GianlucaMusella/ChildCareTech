package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TripManagement {

    public void tappe (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/Stage.fxml"));
        Scene scene = new Scene(root);
        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.setTitle("Tappe");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void partecipantiTrip (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripPartecipant.fxml"));
        Scene scene = new Scene(root);
        javafx.stage.Stage stage = new Stage();
        stage.setTitle("Adesioni Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void appelloTrip (ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripAppeal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Appello Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void back_method(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Menù Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

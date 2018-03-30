package loginScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import serverRMI.InterfaceRMI;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Controller{

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private MenuButton menuConnessione;


    public void connessioneRMI(ActionEvent actionEvent) {
        System.out.println("Ho scelto RMI");
        menuConnessione.setText("RMI");

    }

    public void connessioneSocket(ActionEvent actionEvent) throws RemoteException {
        System.out.println("Ho scelto Socket");
        menuConnessione.setText("Socket");



    }

    /*public void initialize(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Login");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        ObservableList<String> C = FXCollections.observableArrayList("RMI","Socket");
        primaryStage.setScene(scene);
        primaryStage.show();


    }*/

    public void Login(ActionEvent event) throws Exception{
        if(txtUsername.getText().equals("Gianluca") && txtPassword.getText().equals("gianluca95")){
            lblStatus.setText("Login Riuscito");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/loginscreen/Choice.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        }else{
            lblStatus.setText("Login Fallito");
        }
    }

}

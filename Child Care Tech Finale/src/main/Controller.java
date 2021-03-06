package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import interfaces.InterfaceServer;


public class Controller {

    public static String selection = null;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private ChoiceBox menuConnessione;

    public String remoteObjectName = "MainFrame";



    public void initialize(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/login/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Child Care Tech");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        ObservableList<String> C = FXCollections.observableArrayList("RMI", "SOCKET");
        menuConnessione = (ChoiceBox<String>) root.lookup("#menuConnessione");
        menuConnessione.setItems(C);
        menuConnessione.setValue("RMI");
    }



    public void login(ActionEvent actionEvent) throws Exception {
        selection = (String) menuConnessione.getSelectionModel().getSelectedItem();

        if(selection.equals("RMI")){
                System.out.println("E' stata scelta la connessione RMI");
                InterfaceServer Mainframe = Singleton.getInstance().rmiLookup();
                if(Mainframe.login(txtUsername.getText(), txtPassword.getText())) {

                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Child Care Tech");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                }
                else
                    lblStatus.setText("Login fallito");

            } else if(selection.equals("SOCKET")){
                System.out.println("Scelta connessione Socket");
                InterfaceServer interfaceServer = Singleton.getInstance().methodSocket();

               boolean success = interfaceServer.login(txtUsername.getText(), txtPassword.getText());
               System.out.println(success);
                if (success) {
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    //apro la schermata del menù
                    Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Child Care Tech");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();

                } else
                    lblStatus.setText("Login fallito");

            }

    }

}

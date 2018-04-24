package loginScreen;


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
import org.json.simple.JSONObject;
import serverRMI.InterfaceRMI;
import serverRMI.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Controller {
    private static Socket socket = null;
    private static PrintWriter printWriter;


    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;



    @FXML
    private ChoiceBox menuConnessione;

    //JSONObject txt;
    public String remoteObjectName = "MainFrame";

    public void initialize(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/loginScreen/Login.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        ObservableList<String> C = FXCollections.observableArrayList("RMI", "Socket");
        menuConnessione = (ChoiceBox<String>) root.lookup("#menuConnessione");
        menuConnessione.setItems(C);
        menuConnessione.setValue("RMI");
    }



    public void login(ActionEvent actionEvent) throws Exception {

            if(menuConnessione.getValue().equals("RMI")){
                System.out.println("E' stata scelta la connessione RMI");
               // Registry registry = LocateRegistry.getRegistry();
                //InterfaceRMI Mainframe = (InterfaceRMI) registry.lookup(remoteObjectName);
               // InterfaceRMI Mainframe = rmiInt();
                InterfaceRMI Mainframe =Singleton.getInstance().rmiLookup();
                if(Mainframe.login(txtUsername.getText(), txtPassword.getText())) {
                    //se il login ha avuto successo nascono il login
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    //apro la schermata del menù
                    Parent root = FXMLLoader.load(getClass().getResource("/menu/Choice.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
                else
                    lblStatus.setText("Login fallito");

            } else {

                final InetAddress URL = InetAddress.getLocalHost();
                final int porta = 3365;
                socket = new Socket(URL, porta);

                if (loginServerSocket()) {
                    System.out.println("E' stata scelta la connessione Socket");
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    //apro la schermata del menù
                    Parent root = FXMLLoader.load(getClass().getResource("/menu/Choice.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                }
                else {
                    lblStatus.setText("Login fallito");
                }
            }
    }

    private boolean loginServerSocket() throws Exception{

        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        //mando username al server
        printWriter.println(txtUsername.getText());

        //mando psw al server
        printWriter.println(txtPassword.getText());

        //pulisco
        printWriter.flush();

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        String risultato = (String) objectInputStream.readObject();

        if(risultato.equals("true")) {
            return true;
        }

        return false;
    }
}

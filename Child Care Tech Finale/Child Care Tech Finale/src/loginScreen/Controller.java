package loginScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class Controller{

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;


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

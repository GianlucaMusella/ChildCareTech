package loginScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            /*int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();*/
            Parent root = FXMLLoader.load(getClass().getResource("/loginScreen/Login.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
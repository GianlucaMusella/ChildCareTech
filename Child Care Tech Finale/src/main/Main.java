package main;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{

            Controller controller = new Controller();
            controller.initialize(primaryStage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
package menu;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import loginScreen.Controller;

public class InsertSupplier {

    public void logout(ActionEvent ev) throws Exception {
        ((Node) ev.getSource()).getScene().getWindow().hide();
        Controller controller = new Controller();
        Stage stage = new Stage();
        controller.initialize(stage);
    }

}

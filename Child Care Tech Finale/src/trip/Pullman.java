package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;

public class Pullman{

    @FXML
    private TextField txtIdGita;

    public void pullmanCount(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        interfaceRMI.pullmanCount(txtIdGita.getText());
    }

}

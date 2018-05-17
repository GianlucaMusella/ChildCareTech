package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Singleton;
import serverRMI.InterfaceRMI;

public class Pullman{

    @FXML
    private TextField txtIdGita;

    public void pullmanCount(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.pullmanCount(txtIdGita.getText());
    }

}

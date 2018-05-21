package menuFood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;

public class AddSecondDish {

    @FXML
    private TextField nomeSecondo;

    @FXML
    private TextField txtAllergeni;

    public void addSecondDish(ActionEvent actionEvent) throws Exception {

        String nome = nomeSecondo.getText();
        String allergeni = txtAllergeni.getText();

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }
        boolean success = interfaceRMI.addSecondo(nome, allergeni);

        nomeSecondo.clear();
        txtAllergeni.clear();

    }

}


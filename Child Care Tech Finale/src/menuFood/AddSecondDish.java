package menuFood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

public class AddSecondDish {

    @FXML
    private TextField nomeSecondo;

    @FXML
    private TextField txtAllergeni;

    public void addSecondDish(ActionEvent actionEvent) throws Exception {

        String nome = nomeSecondo.getText();
        String allergeni = txtAllergeni.getText();

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        boolean success = interfaceServer.addSecondo(nome, allergeni);

        nomeSecondo.clear();
        txtAllergeni.clear();

    }

}


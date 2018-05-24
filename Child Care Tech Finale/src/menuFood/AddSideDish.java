package menuFood;

import interfaces.InterfaceServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Controller;
import main.Singleton;

public class AddSideDish {

    @FXML
    private TextField nomeContorno;

    @FXML
    private TextField txtAllergeni;


    public void addSideDish(ActionEvent actionEvent) throws Exception{

        String nome = nomeContorno.getText();
        String allergeni = txtAllergeni.getText();

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
        boolean success = interfaceServer.addSide(nome, allergeni);

        nomeContorno.clear();
        txtAllergeni.clear();

    }
}

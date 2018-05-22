package menuFood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

public class AddFirstDish {

    @FXML
    private TextField nomePrimo;

    @FXML
    private TextField txtAllergeni;


    public void addFirstDish(ActionEvent actionEvent) throws Exception{

        String nome = nomePrimo.getText();
        String allergeni = txtAllergeni.getText();

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }        boolean success = interfaceServer.addPrimo(nome, allergeni);

        nomePrimo.clear();
        txtAllergeni.clear();

    }


}

package menuFood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

public class InsertFirstDishes {

    @FXML
    private TextField nomePrimo;

    @FXML
    private TextField txtAllergeni;


    public void addFirstDish(ActionEvent actionEvent) throws Exception{

        String nome = nomePrimo.getText();
        String allergeni = txtAllergeni.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addPrimo(nome, allergeni);

        nomePrimo.clear();
        txtAllergeni.clear();

    }


}

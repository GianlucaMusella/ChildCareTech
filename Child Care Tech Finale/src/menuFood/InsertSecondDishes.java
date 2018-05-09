package menuFood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

public class InsertSecondDishes {

    @FXML
    private TextField nomeSecondo;

    @FXML
    private TextField txtAllergeni;

    public void addSecondDish(ActionEvent actionEvent) throws Exception {

        String nome = nomeSecondo.getText();
        String allergeni = txtAllergeni.getText();

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.addSecondo(nome, allergeni);

        nomeSecondo.clear();
        txtAllergeni.clear();

    }

}

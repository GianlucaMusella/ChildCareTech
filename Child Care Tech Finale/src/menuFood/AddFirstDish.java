package menuFood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.rmi.RemoteException;

public class AddFirstDish {

    @FXML
    private TextField nomePrimo;

    @FXML
    private TextField txtAllergeni;

    @FXML
    private Label lblStatus;

    private InterfaceServer interfaceServer;

    public AddFirstDish(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    public void addFirstDish(ActionEvent actionEvent) throws Exception {
        String nome = nomePrimo.getText();
        String allergeni = txtAllergeni.getText();

        if (nomePrimo.getText().isEmpty() || txtAllergeni.getText().isEmpty()) {
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        }if(interfaceServer.controlAllergy(allergeni)){
            interfaceServer.addAllergy(allergeni);
        } else {
            try {

                boolean success = interfaceServer.addPrimo(nome, allergeni);
                if (success){
                    nomePrimo.clear();
                    txtAllergeni.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}

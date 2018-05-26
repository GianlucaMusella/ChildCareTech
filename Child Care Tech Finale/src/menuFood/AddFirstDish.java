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


    public void addFirstDish(ActionEvent actionEvent) throws Exception {
        if (nomePrimo.getText().isEmpty() || txtAllergeni.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String nome = nomePrimo.getText();
            String allergeni = txtAllergeni.getText();
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
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

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

public class AddSecondDish {

    @FXML
    private TextField nomeSecondo;

    @FXML
    private TextField txtAllergeni;

    @FXML
    private Label lblStatus;

    public void addSecondDish(ActionEvent actionEvent) throws Exception {
        if (nomeSecondo.getText().isEmpty() || txtAllergeni.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String nome = nomeSecondo.getText();
            String allergeni = txtAllergeni.getText();
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
                boolean success = interfaceServer.addSecondo(nome, allergeni);
                if (success) {
                    nomeSecondo.clear();
                    txtAllergeni.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
        } catch (RemoteException e){
                e.printStackTrace();
            }
        }

    }

}


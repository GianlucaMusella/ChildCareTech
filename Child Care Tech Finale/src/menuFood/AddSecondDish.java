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

    private InterfaceServer interfaceServer;

    public AddSecondDish(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    public void addSecondDish(ActionEvent actionEvent) throws Exception {
        String nome = nomeSecondo.getText();
        String allergeni = txtAllergeni.getText();

        if (nomeSecondo.getText().isEmpty() || txtAllergeni.getText().isEmpty()) {
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        }if(interfaceServer.controlAllergy(allergeni)){
            interfaceServer.addAllergy(allergeni);
        }else {

            try {

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


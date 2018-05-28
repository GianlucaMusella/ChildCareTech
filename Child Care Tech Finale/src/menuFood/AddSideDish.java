package menuFood;

import interfaces.InterfaceServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.Controller;
import main.Singleton;

import java.rmi.RemoteException;

public class AddSideDish {

    @FXML
    private TextField nomeContorno;

    @FXML
    private TextField txtAllergeni;

    @FXML
    private Label lblStatus;

    private InterfaceServer interfaceServer;

    public AddSideDish(){
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }
    }

    public void addSideDish(ActionEvent actionEvent) throws Exception{
        String nome = nomeContorno.getText();
        String allergeni = txtAllergeni.getText();

        if (nomeContorno.getText().isEmpty() || txtAllergeni.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {

            try {

                boolean success = interfaceServer.addSide(nome, allergeni);
                if (success) {
                    nomeContorno.clear();
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

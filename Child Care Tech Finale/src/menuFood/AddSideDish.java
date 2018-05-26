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


    public void addSideDish(ActionEvent actionEvent) throws Exception{
        if (nomeContorno.getText().isEmpty() || txtAllergeni.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String nome = nomeContorno.getText();
            String allergeni = txtAllergeni.getText();
            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }
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

package addPeople;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;


public class AddSupplier {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtAzienda;

    @FXML
    private TextField txtFornitura;

    @FXML
    private TextField txtPiva;

    @FXML
    private Button save;

    @FXML
    private Label lblStatus;

    public void addSupplier(ActionEvent actionEvent) throws Exception {
        if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtPiva.getText().isEmpty() && txtPiva.getText().length() == 11 || txtAzienda.getText().isEmpty()
                || txtFornitura.getText().isEmpty())
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        else {
            String name = txtName.getText();
            String surname = txtSurname.getText();
            String azienda = txtAzienda.getText();
            String fornitura = txtFornitura.getText();
            String partitaIva = txtPiva.getText();

            try {
                InterfaceRMI interfaceRMI;

                if (Controller.selection.equals("RMI")) {
                    interfaceRMI = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceRMI = Singleton.getInstance().methodSocket();
                }

                boolean success = interfaceRMI.addSupplier(name, surname, azienda, fornitura, partitaIva);
                if (success) {
                    txtName.clear();
                    txtSurname.clear();
                    txtAzienda.clear();
                    txtFornitura.clear();
                    txtPiva.clear();
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                }
            } catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }
}

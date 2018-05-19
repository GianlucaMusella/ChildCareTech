package addPeople;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    public void addSupplier(ActionEvent actionEvent) throws Exception {
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String azienda = txtAzienda.getText();
        String fornitura = txtFornitura.getText();
        String partitaIva = txtPiva.getText();

        //InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();

        InterfaceRMI interfaceRMI;

        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }

        boolean success = interfaceRMI.addSupplier(name, surname, azienda, fornitura, partitaIva);
        //se metti il cambio del label serve la try catch con remoteexception


        txtName.clear();
        txtSurname.clear();
        txtAzienda.clear();
        txtFornitura.clear();
        txtPiva.clear();

    }
}

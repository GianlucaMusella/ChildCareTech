package dataEntry;

import connectionDatabase.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;
import serverRMI.RMIServer;

import java.rmi.RemoteException;
import java.sql.SQLException;


public class Supplier {

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

    /*private String nome;
    private String cognome;
    private String azienda;
    private String fornitura;
    private String partitaIva;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getFornitura() {
        return fornitura;
    }

    public void setFornitura(String fornitura) {
        this.fornitura = fornitura;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }
*/
    public void addSupplier(ActionEvent actionEvent) throws Exception {
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String azienda = txtAzienda.getText();
        String fornitura = txtFornitura.getText();
        String partitaIva = txtPiva.getText();
        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        boolean success = interfaceRMI.newSupplier(name, surname, azienda, fornitura, partitaIva);

        //se metti il cambio del label serve la try catch con remoteexception

        /*RMIServer server = new RMIServer();
        server.newSupplier(name, surname, azienda, fornitura, partitaIva);*/

        /*ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        connectionDatabase.newSupplier(name, surname, azienda, fornitura, partitaIva);*/

        txtName.clear();
        txtSurname.clear();
        txtAzienda.clear();
        txtFornitura.clear();
        txtPiva.clear();

    }
}

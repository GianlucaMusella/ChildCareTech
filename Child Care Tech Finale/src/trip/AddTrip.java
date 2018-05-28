package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class AddTrip {

    @FXML
    private TextField txtMetaa;

    @FXML
    private TextField txtID;

    @FXML
    private DatePicker datePartenza;

    @FXML
    private DatePicker dateRitorno;

    @FXML
    private Label lblStatus;



    public void newTrip (ActionEvent actionEvent) throws Exception {

        String id = txtID.getText();
        String meta = txtMetaa.getText();
        LocalDate andata = datePartenza.getValue();
        LocalDate ritorno = dateRitorno.getValue();


        if (txtMetaa.getText().isEmpty() || txtID.getText().isEmpty() || datePartenza.getValue() == null || dateRitorno.getValue() == null) {
            lblStatus.setText("ERRORE: Dati obbligatori mancanti");
        }if(andata.isAfter(ritorno)){
            lblStatus.setText("La data di andata è successiva a quella di ritorno");
        }else {

            try {
                InterfaceServer interfaceServer;
                if (Controller.selection.equals("RMI")) {
                    interfaceServer = Singleton.getInstance().rmiLookup();
                } else {
                    interfaceServer = Singleton.getInstance().methodSocket();
                }

                boolean success = interfaceServer.newTrip(id, meta, andata, ritorno);
                if (success) {
                    lblStatus.setTextFill(Color.BLACK);
                    lblStatus.setText("Inserimento riuscito");
                    txtID.clear();
                    txtMetaa.clear();
                    datePartenza.getEditor().clear();
                    dateRitorno.getEditor().clear();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/trip/TripOrganize.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Organizza Gita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}



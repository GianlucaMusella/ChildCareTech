package trip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import interfaces.InterfaceServer;

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

    public void newTrip (ActionEvent actionEvent) throws Exception {

        String id = txtID.getText();
        String meta = txtMetaa.getText();
        LocalDate andata = datePartenza.getValue();
        LocalDate ritorno = dateRitorno.getValue();

        InterfaceServer interfaceServer;
        if (Controller.selection.equals("RMI")) {
            interfaceServer = Singleton.getInstance().rmiLookup();
        } else {
            interfaceServer = Singleton.getInstance().methodSocket();
        }

        boolean success = interfaceServer.newTrip(id, meta, andata, ritorno);

        txtID.clear();
        txtMetaa.clear();
        datePartenza.getEditor().clear();
        dateRitorno.getEditor().clear();

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



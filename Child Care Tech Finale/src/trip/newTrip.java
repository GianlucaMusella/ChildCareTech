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
import serverRMI.InterfaceRMI;

import java.time.LocalDate;

public class newTrip {

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

        InterfaceRMI interfaceRMI;
        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }

        boolean success = interfaceRMI.newTrip(id, meta, andata, ritorno);

        txtID.clear();
        txtMetaa.clear();
        datePartenza.getEditor().clear();
        dateRitorno.getEditor().clear();

    }

    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/TripMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}



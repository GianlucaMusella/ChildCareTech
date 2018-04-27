package modify;

import dataEntry.ChildGS;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ModifyClient  {

    @FXML
    private TextField txtCodicefiscaleOld;
    @FXML
    private TextField txtCodicefiscaleNew;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    @FXML
    private TextField txtLuogo;
    @FXML
    private TextField txtIdbambino;
    @FXML
    private DatePicker dateData;

    public void modifyClient (javafx.event.ActionEvent actionEvent) throws Exception {

       /*
        LocalDate localDate = dateData.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date datadinascita = Date.from(instant); */

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.modifyChild(txtCodicefiscaleOld.getText(), txtNome.getText(), txtCognome.getText(), txtCodicefiscaleNew.getText(), txtLuogo.getText(), txtIdbambino.getText(), dateData.getValue());

    }
}
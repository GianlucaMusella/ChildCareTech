package modify;

import dataEntry.ChildGS;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    public void modifyClient (javafx.event.ActionEvent actionEvent){

        LocalDate localDate = dateData.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date datadinascita = Date.from(instant);

        ChildGS newer = new ChildGS(txtNome.getText(), txtCognome.getText(), txtCodicefiscaleNew.getText(), txtLuogo.getText(), datadinascita, txtIdbambino.getText());

        /*
        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        InterfaceRMI.modifyChild(txtCodicefiscaleOld.getText(), newer);
         */
    }
}

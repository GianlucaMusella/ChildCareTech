package searchModifyDelete;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import loginScreen.Singleton;
import serverRMI.InterfaceRMI;


public class ModifyClient{

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
    private DatePicker dateData;

    public void modifyClient (javafx.event.ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.modifyChild(txtCodicefiscaleOld.getText(), txtCodicefiscaleNew.getText(), txtNome.getText(), txtCognome.getText(), txtLuogo.getText(), dateData.getValue());

    }
}

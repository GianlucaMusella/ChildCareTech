package addPeople;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Controller;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.time.LocalDate;

public class AddDoctor {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private TextField txtLuogo;

    @FXML
    private DatePicker dataData;

    @FXML
    private TextField txtSesso;

    @FXML
    private RadioButton radioMaschio;

    @FXML
    private RadioButton radioFemmina;


    public void addDoctor(ActionEvent actionEvent) throws Exception {
        String sesso;
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        String luogo = txtLuogo.getText();
        LocalDate data = dataData.getValue();
        //String sesso = txtSesso.getText();
        String sessoM = radioMaschio.getText();
        String sessoF = radioFemmina.getText();

        if(radioMaschio.isSelected()) {
            sesso = sessoM;
        }else {

            sesso = sessoF;
        }

        InterfaceRMI interfaceRMI;

        if (Controller.selection.equals("RMI")) {
            interfaceRMI = Singleton.getInstance().rmiLookup();
        } else {
            interfaceRMI = Singleton.getInstance().methodSocket();
        }

        boolean success = interfaceRMI.addDoctor(codiceFiscale, nome, cognome, data, luogo, sesso);

        //se metti il cambio del label serve la try catch con remoteexception


        txtNome.clear();
        txtCognome.clear();
        txtCodiceFiscale.clear();
        txtLuogo.clear();
        dataData.getEditor().clear();
        txtSesso.clear();
    }


    public void back_method(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/DoctorMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void menuPrincipale(ActionEvent actionEvent) throws Exception{

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/gui/menu/Choice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}

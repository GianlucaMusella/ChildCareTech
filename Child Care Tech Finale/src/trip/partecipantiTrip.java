package trip;

import getterAndSetter.people.ChildGS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class partecipantiTrip implements Initializable{

    @FXML
    private TableView<ChildGS> tableBambini;

    @FXML
    private TableColumn<ChildGS, String> columnNome;

    @FXML
    private TableColumn<ChildGS, String> columnCognome;

    @FXML
    private TableColumn<ChildGS, String> columnCodiceFiscale;

    @FXML
    private TableColumn<ChildGS, String> columnID;

    @FXML
    private TextField txtCodicefiscale;

    @FXML
    private TextField txtIDgita;

    @FXML
    private TextField idBambino;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodiceFiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("idBambino"));

        tableBambini.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableBambini.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if(newSelection != null){
                txtCodicefiscale.setText(newSelection.getCodiceFiscale());
                idBambino.setText(newSelection.getIdBambino());
            }
        });

        tableBambini.getItems().clear();
    }

    public void partecipantiTrip (ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.newpartecipanteTrip(txtCodicefiscale.getText(), txtIDgita.getText(), idBambino.getText());

    }

    public void viewInfo(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<ChildGS> childrenGS = interfaceRMI.viewChild();

        tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
        tableBambini.setItems(FXCollections.observableArrayList(childrenGS));

    }


}

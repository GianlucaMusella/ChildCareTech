package trip;

// Database sistemato con relazione 1 a n tra bambini_partecipanti e presenti


import connectionDatabase.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Singleton;
import serverRMI.InterfaceRMI;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class appelloTrip implements Initializable{

    @FXML
    private TextField idGita;

    @FXML
    private TableView<AppelloGS> tableAppello;

    @FXML
    private TableColumn<AppelloGS, String> columnNome;

    @FXML
    private TableColumn<AppelloGS, String> columnCognome;

    @FXML
    private TableColumn<AppelloGS, String> columnCodicefiscale;

    @FXML
    private TableColumn<AppelloGS, String> columnPresenza;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        columnCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
        columnPresenza.setCellValueFactory(new PropertyValueFactory<>("presenza"));

        tableAppello.getItems().clear();
    }
    public void loadData(ActionEvent actionEvent) throws Exception {

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        ArrayList<AppelloGS> appelloGSArrayList = interfaceRMI.loadDataServer(Integer.parseInt(idGita.getText()));  //Qui vado a chiamare la parte Server che scrivo in fondo al codice come ieri

        tableAppello.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableAppello.setItems(FXCollections.observableArrayList(appelloGSArrayList));

    }

    public void appelloTrip (ActionEvent actionEvent) throws Exception {
        /*
        ArrayList<AppelloGS> modificaPresenza = tableAppello.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        SI RIUSCISSE A FARE CON LA MULTISELECT SAREBBE MOLTO MEGLIO, MA DOVREI PROVARE PER CAPIRE COME FUNZIONA
         */
        AppelloGS bambinoPresente = tableAppello.getSelectionModel().getSelectedItem();
        String codiceFiscale = bambinoPresente.getCodiceFiscale();

        System.out.println(codiceFiscale); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.bambinoPresenteServer(codiceFiscale, Integer.parseInt(idGita.getText()));
    }

    public void assenza (ActionEvent actionEvent) throws Exception {
        /*
        ArrayList<AppelloGS> modificaPresenza = tableAppello.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        SI RIUSCISSE A FARE CON LA MULTISELECT SAREBBE MOLTO MEGLIO, MA DOVREI PROVARE PER CAPIRE COME FUNZIONA
         */
        AppelloGS bambinoAssente = tableAppello.getSelectionModel().getSelectedItem();
        String codiceFiscale = bambinoAssente.getCodiceFiscale();

        System.out.println(codiceFiscale); // Ho messo questo per capire se prende il codice fiscale giusto

        InterfaceRMI interfaceRMI = Singleton.getInstance().rmiLookup();
        interfaceRMI.bambinoAssenteServer(codiceFiscale, Integer.parseInt(idGita.getText()));
    }

    public void back_method(ActionEvent actionEvent) throws Exception{
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/trip/TripMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }






/*

    // PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA PARTE SERVER LOAD DATA
     public ArrayList<AppelloGS> loadDataServer (int idGita) throws SQLException {
         ArrayList<AppelloGS> values = new ArrayList<>();
         ConnectionDatabase connectionDatabase = new ConnectionDatabase();
         Statement stmt = connectionDatabase.initializeConnection().createStatement();
         String SQL = ("SELECT mydb.bambini.Nome, mydb.bambini.Cognome, mydb.bambini,CodiceFiscale, mydb.bambini_has_gita.Presenza ((FROM mydb.bambini_has_gita" +
                 "INNER JOIN mydb.bambini ON mydb.bambini.idBambino = mydb.bambini_has_gita.Bambini_idBambino)" +
                 "INNER JOIN mydb.gita ON mydb.gita.idGita = mydb.bambini_has_gita.Gita_idGita = ");
         ResultSet rs = stmt.executeQuery(SQL + idGita + ")");
         while (rs.next()){
             String colonnaNome = rs.getString("Nome");
             String colonnaCognome = rs.getString ("Cognome");
             String colonnaCodicefiscale = rs.getString("CodiceFiscale");
             String colonnaPresenza;
             if (rs.getBoolean("Presenza"))
                 colonnaPresenza = ("Presente");
             else
                 colonnaPresenza = ("Assente");

             values.add(new AppelloGS(colonnaNome, colonnaCognome, colonnaCodicefiscale, colonnaPresenza));
         }
         return values;
     }
     public void bambinoPresenteServer (String CodiceFiscale) throws SQLException {
         ConnectionDatabase connectionDatabase = new ConnectionDatabase();
         Statement stmt = connectionDatabase.initializeConnection().createStatement();
         int idBambino;
         String JoinSQL = ("SELECT * FROM mydb.bambini WHERE CodiceFiscale = '");
         ResultSet rs = stmt.executeQuery(JoinSQL + CodiceFiscale + "')");
         idBambino = rs.getInt("idBambino");
         String SQL = ("UPDATE mydb.bambini_has_gita SET Presenza = 1 WHERE mydb.bambini_has_gita.Bambini_idBambino = ");
         int n = stmt.executeUpdate(SQL + idBambino + ")");
     }
*/
}

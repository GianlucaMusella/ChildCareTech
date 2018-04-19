package dataEntry;

import connectionDatabase.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class NewChild {

    @FXML
    private Label lblError;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    @FXML
    private TextField txtCodiceFiscale;
    @FXML
    private TextField txtLuogo;
    @FXML
    private DatePicker dateData;
    @FXML
    private TextField txtGenitore1;
    @FXML
    private TextField txtGenitore2;
    @FXML
    private TextField txtContatto;
    @FXML
    private TextField txtPediatra;

    @FXML
    private TableView tableBambini;
    @FXML
    private TableColumn columnNome;
    @FXML
    private TableColumn columnCognome;
    @FXML
    private TableColumn columnCodiceFiscale;


    public Child NewChild (ActionEvent event) {
        //quando viene premuto il bottono conferma devo cotnrollare che i campi NOT NULL siano pieni, e poi
        // memorizzare i dati
        Child child;
        child = new Child();
        if(txtCodiceFiscale.getText().equals("") || txtNome.getText().equals("") || txtCognome.getText().equals("") ||
                txtLuogo.getText().equals("") || txtContatto.getText().equals("") || txtPediatra.getText().equals(""))
        {
            lblError.setText("Errore, inserire tutti i dati");
            child = NewChild(event);
        }
        else {
            child.setNome(txtNome.getText());
            child.setCognome(txtCognome.getText());
            child.setCodiceFiscale(txtCodiceFiscale.getText());
            child.setLuogoDiNascita(txtLuogo.getText());


            LocalDate localDate = dateData.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date datadinascita = Date.from(instant);

            // Il DatePicker mi restituisce una local date e io ho bisogno di un date, devo fare questa cosa per sistemarlo

            child.setData(datadinascita);
            child.setCodicefiscalegen1(txtGenitore1.getText());
            child.setCodicefiscalegen2(txtGenitore2.getText());
            child.setCodicefiscalecontatto(txtContatto.getText());
            child.setCodicefiscalepediatra(txtPediatra.getText()); // qui c'è l'errore perché io prendo il testo ma ID pediatra è un int

        }


        return child;


    }


}

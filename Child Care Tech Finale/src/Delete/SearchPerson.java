package Delete;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.xml.transform.Result;
import java.awt.*;
import java.sql.*;

public class SearchPerson {
    @FXML
    private TextField codicefiscale;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TableView tableBambini;
    @FXML
    private TableColumn columnNome;
    @FXML
    private TableColumn columnCognome;
    @FXML
    private TableColumn columnCodicefiscale;
    @FXML
    private TableColumn columnDatadinascita;
    @FXML
    private TableColumn columnLuogodinascita;

    public void searchChild() throws SQLException {

        String name = nome.getText();
        String surname = cognome.getText();
        String cod = codicefiscale.getText();
        String sql = ("SELECT * FROM BAMBINI WHERE ");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb");
        Statement stmt = conn.createStatement();


        if (surname.isEmpty() && cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "NOME = '" + name + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("Codice Fiscale");
                String colonnaluogodinascita = rs.getString("Luogo di nascita");
                Date colonnadatadinascita = rs.getDate("Data di nascita");

                //  E qui devo popolare la tabella
            }
        } else if (name.isEmpty() && cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "COGNOME = '" + surname + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("Codice Fiscale");
                String colonnaluogodinascita = rs.getString("Luogo di nascita");
                Date colonnadatadinascita = rs.getDate("Data di nascita");

                //  E qui devo popolare la tabella
            }
        } else if (name.isEmpty() && surname.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CODICE FISCALE = '" + cod + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("Codice Fiscale");
                String colonnaluogodinascita = rs.getString("Luogo di nascita");
                Date colonnadatadinascita = rs.getDate("Data di nascita");

                //  E qui devo popolare la tabella
            }
        } else if (name.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CODICE FISCALE = '" + cod + "' AND COGNOME = '" + surname + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("Codice Fiscale");
                String colonnaluogodinascita = rs.getString("Luogo di nascita");
                Date colonnadatadinascita = rs.getDate("Data di nascita");

                //  E qui devo popolare la tabella
            }
        } else if (surname.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "CODICE FISCALE = '" + cod + "' AND NOME = '" + name + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("Codice Fiscale");
                String colonnaluogodinascita = rs.getString("Luogo di nascita");
                Date colonnadatadinascita = rs.getDate("Data di nascita");

                //  E qui devo popolare la tabella
            }
        } else if (cod.isEmpty()) {
            ResultSet rs = stmt.executeQuery(sql + "NOME = '" + name + "' AND COGNOME = '" + surname + "'");
            while (rs.next()) {
                String colonnanome = rs.getString("Nome");
                String colonnacognome = rs.getString("Cognome");
                String colonnacodicefiscale = rs.getString("Codice Fiscale");
                String colonnaluogodinascita = rs.getString("Luogo di nascita");
                Date colonnadatadinascita = rs.getDate("Data di nascita");

                //  E qui devo popolare la tabella
            }
        }
    }
}
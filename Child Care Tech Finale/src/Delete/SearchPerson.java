package Delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<elemTable> tableBambini;
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

    private class elemTable {
        private String nome;
        private String cognome;
        private String codfisc;
        private String luogo;
        private Date data;

        public elemTable (String nome, String cognome, String codfisc, String luogo, Date data){
            this.nome = nome;
            this.cognome = cognome;
            this.codfisc = codfisc;
            this.luogo = luogo;
            this.data = data;
            /*


        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getCodfisc() {
            return codfisc;
        }

        public void setCodfisc(String codfisc) {
            this.codfisc = codfisc;
        }

        public String getLuogo() {
            return luogo;
        }

        public void setLuogo(String luogo) {
            this.luogo = luogo;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }
    }
             */
        }
    }

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

                Label lblValue = new Label();
                ObservableList<elemTable> values = FXCollections.observableArrayList();
                values.add(new elemTable(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita));
                tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
                tableBambini.setItems(values);
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

                Label lblValue = new Label();
                ObservableList<elemTable> values = FXCollections.observableArrayList();
                values.add(new elemTable(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita));
                tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
                tableBambini.setItems(values);
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

                Label lblValue = new Label();
                ObservableList<elemTable> values = FXCollections.observableArrayList();
                values.add(new elemTable(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita));
                tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
                tableBambini.setItems(values);
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

                Label lblValue = new Label();
                ObservableList<elemTable> values = FXCollections.observableArrayList();
                values.add(new elemTable(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita));
                tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
                tableBambini.setItems(values);
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

                Label lblValue = new Label();
                ObservableList<elemTable> values = FXCollections.observableArrayList();
                values.add(new elemTable(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita));
                tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
                tableBambini.setItems(values);
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

                Label lblValue = new Label();
                ObservableList<elemTable> values = FXCollections.observableArrayList();
                values.add(new elemTable(colonnanome, colonnacognome, colonnacodicefiscale, colonnaluogodinascita, colonnadatadinascita));
                tableBambini.setColumnResizePolicy(tableBambini.CONSTRAINED_RESIZE_POLICY);
                tableBambini.setItems(values);
            }
        }
    }
}
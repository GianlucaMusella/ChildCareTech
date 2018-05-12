package getterAndSetter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class StaffGUI {

    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty codiceFiscale;
    private StringProperty mansione;

    public StaffGUI(StaffGS staffGS){

        this.nome = new SimpleStringProperty(staffGS.getNome());
        this.cognome = new SimpleStringProperty(staffGS.getCognome());
        this.codiceFiscale = new SimpleStringProperty(staffGS.getCodiceFiscale());
        this.mansione = new SimpleStringProperty(staffGS.getMansione());
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCognome() {
        return cognome.get();
    }

    public StringProperty cognomeProperty() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome.set(cognome);
    }

    public String getCodiceFiscale() {
        return codiceFiscale.get();
    }

    public StringProperty codiceFiscaleProperty() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale.set(codiceFiscale);
    }

    public String getMansione() {
        return mansione.get();
    }

    public StringProperty mansioneProperty() {
        return mansione;
    }

    public void setMansione(String mansione) {
        this.mansione.set(mansione);
    }
}

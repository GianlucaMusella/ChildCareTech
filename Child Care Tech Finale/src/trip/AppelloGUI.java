package trip;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AppelloGUI {

    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty codiceFiscale;
    private StringProperty presenza;

    public void AppelloGUI(AppealGS appelloGS){

        this.nome = new SimpleStringProperty(appelloGS.getNome());
        this.cognome = new SimpleStringProperty(appelloGS.getCognome());
        this.codiceFiscale = new SimpleStringProperty(appelloGS.getCodiceFiscale());
        this.presenza = new SimpleStringProperty(appelloGS.getPresenza());
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

    public String getPresenza() {
        return presenza.get();
    }

    public StringProperty presenzaProperty() {
        return presenza;
    }

    public void setPresenza(String presenza) {
        this.presenza.set(presenza);
    }
}

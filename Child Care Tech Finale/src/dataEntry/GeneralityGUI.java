package dataEntry;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class GeneralityGUI {

    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty codiceFiscale;
    private StringProperty luogoDiNascita;
    private StringProperty data;

    public GeneralityGUI(Generality generality){
        this.nome = new SimpleStringProperty(generality.getNome());
        this.cognome = new SimpleStringProperty(generality.getCognome());
        this.codiceFiscale = new SimpleStringProperty(generality.getCodiceFiscale());
        this.luogoDiNascita = new SimpleStringProperty(generality.getLuogoDiNascita());
        //this.data = new SimpleStringProperty(generality.());

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

    public String getLuogoDiNascita() {
        return luogoDiNascita.get();
    }

    public StringProperty luogoDiNascitaProperty() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita.set(luogoDiNascita);
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }
}

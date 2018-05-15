package getterAndSetter.people;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChildGUI {

    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty codiceFiscale;
    private StringProperty luogoDiNascita;
    private StringProperty data;
    private StringProperty idBambino;

    public ChildGUI(ChildGS childGS){
        this.nome = new SimpleStringProperty(childGS.getNome());
        this.cognome = new SimpleStringProperty(childGS.getCognome());
        this.codiceFiscale = new SimpleStringProperty(childGS.getCodiceFiscale());
        //this.luogoDiNascita = new SimpleStringProperty(childGS.getLuogoDiNascita());
        //this.data = new SimpleStringProperty(generality.());
        this.idBambino = new SimpleStringProperty(childGS.getIdBambino());

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

    public String getIdBambino() {
        return idBambino.get();
    }

    public StringProperty idBambinoProperty() {
        return idBambino;
    }

    public void setIdBambino(String idBambino) {
        this.idBambino.set(idBambino);
    }
}

package dataEntry;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorGUI {

    private StringProperty nome;
    private StringProperty codiceFiscale;

    public DoctorGUI(Doctor doctor){
        this.nome = new SimpleStringProperty(doctor.getNome());
        this.codiceFiscale = new SimpleStringProperty(doctor.getCodiceFiscale());
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

    public String getCodiceFiscale() {
        return codiceFiscale.get();
    }

    public StringProperty codiceFiscaleProperty() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale.set(codiceFiscale);
    }
}

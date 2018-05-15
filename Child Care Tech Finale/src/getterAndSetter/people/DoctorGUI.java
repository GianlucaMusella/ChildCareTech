package getterAndSetter.people;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorGUI {

    private StringProperty nome;
    private StringProperty codiceFiscale;

    public DoctorGUI(DoctorGS doctorGS){
        this.nome = new SimpleStringProperty(doctorGS.getNome());
        this.codiceFiscale = new SimpleStringProperty(doctorGS.getCodiceFiscale());
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

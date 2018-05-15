package getterAndSetter.people;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactGUI {

    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty codiceFiscale;

    public ContactGUI(ContactGS contactGS){
        this.nome = new SimpleStringProperty(contactGS.getNome());
        this.cognome = new SimpleStringProperty(contactGS.getCognome());
        this.codiceFiscale = new SimpleStringProperty(contactGS.getCodiceFiscale());
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

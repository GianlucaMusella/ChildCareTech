package getterAndSetter.food;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BambiniAllergiciGUI {

    private StringProperty nomeBambino;
    private StringProperty cognomeBambino;
    private StringProperty nomeAllergene;
    private StringProperty nomePrimo;
    private StringProperty nomeSecondo;
    private StringProperty nomeContorno;

    public BambiniAllergiciGUI(BambiniAllergici bambiniAllergici){

        this.nomeBambino = new SimpleStringProperty(bambiniAllergici.getNomeBambino());
        this.cognomeBambino = new SimpleStringProperty(bambiniAllergici.getCognomeBambino());
        this.nomeAllergene = new SimpleStringProperty(bambiniAllergici.getNomeAllergene());
        this.nomePrimo = new SimpleStringProperty(bambiniAllergici.getNomePrimo());
        this.nomeSecondo = new SimpleStringProperty(bambiniAllergici.getNomeSecondo());
        this.nomeContorno = new SimpleStringProperty(bambiniAllergici.getNomeContorno());
    }

    public String getNomeBambino() {
        return nomeBambino.get();
    }

    public StringProperty nomeBambinoProperty() {
        return nomeBambino;
    }

    public void setNomeBambino(String nomeBambino) {
        this.nomeBambino.set(nomeBambino);
    }

    public String getCognomeBambino() {
        return cognomeBambino.get();
    }

    public StringProperty cognomeBambinoProperty() {
        return cognomeBambino;
    }

    public void setCognomeBambino(String cognomeBambino) {
        this.cognomeBambino.set(cognomeBambino);
    }

    public String getNomeAllergene() {
        return nomeAllergene.get();
    }

    public StringProperty nomeAllergeneProperty() {
        return nomeAllergene;
    }

    public void setNomeAllergene(String nomeAllergene) {
        this.nomeAllergene.set(nomeAllergene);
    }

    public String getNomePrimo() {
        return nomePrimo.get();
    }

    public StringProperty nomePrimoProperty() {
        return nomePrimo;
    }

    public void setNomePrimo(String nomePrimo) {
        this.nomePrimo.set(nomePrimo);
    }

    public String getNomeSecondo() {
        return nomeSecondo.get();
    }

    public StringProperty nomeSecondoProperty() {
        return nomeSecondo;
    }

    public void setNomeSecondo(String nomeSecondo) {
        this.nomeSecondo.set(nomeSecondo);
    }

    public String getNomeContorno() {
        return nomeContorno.get();
    }

    public StringProperty nomeContornoProperty() {
        return nomeContorno;
    }

    public void setNomeContorno(String nomeContorno) {
        this.nomeContorno.set(nomeContorno);
    }
}

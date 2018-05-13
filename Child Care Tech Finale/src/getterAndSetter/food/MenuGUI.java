package getterAndSetter.food;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class MenuGUI {

    private StringProperty nomeMenu;
    private StringProperty nomePrimo;
    private StringProperty nomeSecondo;
    private ObjectProperty<LocalDate> giorno;


    public MenuGUI(MenuGS menuGS){

        this.nomeMenu = new SimpleStringProperty(menuGS.getNomeMenu());
        this.nomePrimo = new SimpleStringProperty(menuGS.getNomePrimo());
        this.nomeSecondo = new SimpleStringProperty(menuGS.getNomeSecondo());
        this.giorno = new SimpleObjectProperty(menuGS.getGiorno());

    }

    public String getNomeMenu() {
        return nomeMenu.get();
    }

    public StringProperty nomeMenuProperty() {
        return nomeMenu;
    }

    public void setNomeMenu(String nomeMenu) {
        this.nomeMenu.set(nomeMenu);
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

    public LocalDate getGiorno() {
        return giorno.get();
    }

    public ObjectProperty<LocalDate> giornoProperty() {
        return giorno;
    }

    public void setGiorno(LocalDate giorno) {
        this.giorno.set(giorno);
    }
}

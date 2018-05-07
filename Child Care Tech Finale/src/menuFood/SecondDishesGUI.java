package menuFood;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SecondDishesGUI {

    private StringProperty nomeSecondo;
    private StringProperty allergene;

    public SecondDishesGUI(SecondDishesGS secondDishesGS){

        this.nomeSecondo = new SimpleStringProperty(secondDishesGS.getNomeSecondo());
        this.allergene = new SimpleStringProperty(secondDishesGS.getAllergene());

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

    public String getAllergene() {
        return allergene.get();
    }

    public StringProperty allergeneProperty() {
        return allergene;
    }

    public void setAllergene(String allergene) {
        this.allergene.set(allergene);
    }
}

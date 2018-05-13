package getterAndSetter.food;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SecondDishGUI {

    private StringProperty nomeSecondo;
    private StringProperty allergene;

    public SecondDishGUI(SecondDishGS secondDishGS){

        this.nomeSecondo = new SimpleStringProperty(secondDishGS.getNomeSecondo());
        this.allergene = new SimpleStringProperty(secondDishGS.getAllergene());

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

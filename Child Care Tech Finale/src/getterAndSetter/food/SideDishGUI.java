package getterAndSetter.food;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SideDishGUI {

    private StringProperty nomeContorno;
    private StringProperty allergene;


    public SideDishGUI(SideDishGS sideDishGS){

        this.nomeContorno = new SimpleStringProperty(sideDishGS.getNomeContorno());
        this.allergene = new SimpleStringProperty(sideDishGS.getAllergene());
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

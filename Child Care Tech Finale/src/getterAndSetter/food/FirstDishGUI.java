package getterAndSetter.food;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FirstDishGUI {

    private StringProperty nomePrimo;
    private StringProperty allergene;


    public FirstDishGUI(FirstDishGS firstDishGS){

        this.nomePrimo = new SimpleStringProperty(firstDishGS.getNomePrimo());
        this.allergene = new SimpleStringProperty(firstDishGS.getAllergene());
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

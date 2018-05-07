package menuFood;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FirstDishesGUI {

    private StringProperty nomePrimo;
    private StringProperty allergene;


    public FirstDishesGUI(FirstDishesGS firstDishesGS){

        this.nomePrimo = new SimpleStringProperty(firstDishesGS.getNomePrimo());
        this.allergene = new SimpleStringProperty(firstDishesGS.getAllergene());
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

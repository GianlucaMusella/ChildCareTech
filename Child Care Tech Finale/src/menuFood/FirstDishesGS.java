package menuFood;

import java.io.Serializable;

public class FirstDishesGS implements Serializable{

    private String nomePrimo;
    private String allergene;


    public FirstDishesGS(String nomePrimo, String allergene){

        this.nomePrimo = nomePrimo;
        this.allergene = allergene;

    }

    public String getNomePrimo() {
        return nomePrimo;
    }

    public void setNomePrimo(String nomePrimo) {
        this.nomePrimo = nomePrimo;
    }

    public String getAllergene() {
        return allergene;
    }

    public void setAllergene(String allergene) {
        this.allergene = allergene;
    }
}

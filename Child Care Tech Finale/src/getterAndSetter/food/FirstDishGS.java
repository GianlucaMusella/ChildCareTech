package getterAndSetter.food;

import java.io.Serializable;

public class FirstDishGS implements Serializable{

    private String nomePrimo;
    private String allergene;


    public FirstDishGS(String nomePrimo, String allergene){

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

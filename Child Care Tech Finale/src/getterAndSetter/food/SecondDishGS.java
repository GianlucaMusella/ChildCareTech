package getterAndSetter.food;

import java.io.Serializable;

public class SecondDishGS implements Serializable {

    private String nomeSecondo;
    private String allergene;

    public SecondDishGS(String nomeSecondo, String allergene){

        this.nomeSecondo = nomeSecondo;
        this.allergene = allergene;

    }

    public String getNomeSecondo() {
        return nomeSecondo;
    }

    public void setNomeSecondo(String nomeSecondo) {
        this.nomeSecondo = nomeSecondo;
    }

    public String getAllergene() {
        return allergene;
    }

    public void setAllergene(String allergeni) {
        this.allergene = allergeni;
    }
}

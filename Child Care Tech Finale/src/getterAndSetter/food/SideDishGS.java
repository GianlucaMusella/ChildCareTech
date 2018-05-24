package getterAndSetter.food;

import java.io.Serializable;

public class SideDishGS implements Serializable {

    private String nomeContorno;
    private String allergene;


    public SideDishGS(String nomeContorno, String allergene){

        this.nomeContorno = nomeContorno;
        this.allergene = allergene;

    }

    public String getNomeContorno() {
        return nomeContorno;
    }

    public void setNomeContorno(String nomeContorno) {
        this.nomeContorno = nomeContorno;
    }

    public String getAllergene() {
        return allergene;
    }

    public void setAllergene(String allergene) {
        this.allergene = allergene;
    }
}

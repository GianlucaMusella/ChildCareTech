package getterAndSetter.food;

import java.io.Serializable;

public class AllergyPeopleGS implements Serializable{

    private String allergieBambini;
    private String allergiePersonale;

    public AllergyPeopleGS(String allergieBambini, String allergiePersonale){

        this.allergieBambini = allergieBambini;
        this.allergiePersonale = allergiePersonale;

    }

    public String getAllergieBambini() {
        return allergieBambini;
    }

    public void setAllergieBambini(String allergieBambini) {
        this.allergieBambini = allergieBambini;
    }

    public String getAllergiePersonale() {
        return allergiePersonale;
    }

    public void setAllergiePersonale(String allergiePersonale) {
        this.allergiePersonale = allergiePersonale;
    }
}

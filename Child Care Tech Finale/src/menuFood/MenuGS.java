package menuFood;

import java.io.Serializable;

public class MenuGS implements Serializable{

    private String allergieBambini;
    private String allergiePersonale;

    public MenuGS(String allergieBambini, String allergiePersonale){

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

package getterAndSetter.food;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AllergyPeopleGUI {

    private StringProperty allergieBambini;
    private StringProperty allergiePersonale;

    public AllergyPeopleGUI(AllergyPeopleGS allergyPeopleGS){

        this.allergieBambini = new SimpleStringProperty(allergyPeopleGS.getAllergieBambini());
        this.allergiePersonale = new SimpleStringProperty(allergyPeopleGS.getAllergiePersonale());

    }

    public String getAllergieBambini() {
        return allergieBambini.get();
    }

    public StringProperty allergieBambiniProperty() {
        return allergieBambini;
    }

    public void setAllergieBambini(String allergieBambini) {
        this.allergieBambini.set(allergieBambini);
    }

    public String getAllergiePersonale() {
        return allergiePersonale.get();
    }

    public StringProperty allergiePersonaleProperty() {
        return allergiePersonale;
    }

    public void setAllergiePersonale(String allergiePersonale) {
        this.allergiePersonale.set(allergiePersonale);
    }
}

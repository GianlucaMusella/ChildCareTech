package menuFood;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AllergyGUI {

    private StringProperty allergieBambini;
    private StringProperty allergiePersonale;

    public AllergyGUI(AllergyGS allergyGS){

        this.allergieBambini = new SimpleStringProperty(allergyGS.getAllergieBambini());
        this.allergiePersonale = new SimpleStringProperty(allergyGS.getAllergiePersonale());

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

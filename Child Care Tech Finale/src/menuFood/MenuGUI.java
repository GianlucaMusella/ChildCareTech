package menuFood;

import dataEntry.ChildGS;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuGUI {

    private StringProperty allergieBambini;
    private StringProperty allergiePersonale;

    public MenuGUI(MenuGS menuGS){

        this.allergieBambini = new SimpleStringProperty(menuGS.getAllergieBambini());
        this.allergiePersonale = new SimpleStringProperty(menuGS.getAllergiePersonale());

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

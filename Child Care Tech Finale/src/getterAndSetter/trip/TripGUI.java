package getterAndSetter.trip;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class TripGUI {

    private StringProperty id;
    private StringProperty meta;
    private ObjectProperty<LocalDate> andata;
    private ObjectProperty<LocalDate> ritorno;
    private StringProperty pullman;


    public void TripGUI(TripGS tripGS){

        this.id = new SimpleStringProperty(tripGS.getId());
        this.meta = new SimpleStringProperty(tripGS.getMeta());
        this.andata = new SimpleObjectProperty(tripGS.getAndata());
        this.ritorno = new SimpleObjectProperty(tripGS.getRitorno());
        this.pullman = new SimpleStringProperty(tripGS.getPullman());
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getMeta() {
        return meta.get();
    }

    public StringProperty metaProperty() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta.set(meta);
    }

    public LocalDate getAndata() {
        return andata.get();
    }

    public ObjectProperty<LocalDate> andataProperty() {
        return andata;
    }

    public void setAndata(LocalDate andata) {
        this.andata.set(andata);
    }

    public LocalDate getRitorno() {
        return ritorno.get();
    }

    public ObjectProperty<LocalDate> ritornoProperty() {
        return ritorno;
    }

    public void setRitorno(LocalDate ritorno) {
        this.ritorno.set(ritorno);
    }
}

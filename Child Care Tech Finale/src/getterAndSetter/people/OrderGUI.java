package getterAndSetter.people;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderGUI {

    private StringProperty azienda;
    private StringProperty ordine;
    private StringProperty quantità;

    public OrderGUI(OrderGS orderGS){

        this.azienda = new SimpleStringProperty(orderGS.getAzienda());
        this.ordine = new SimpleStringProperty(orderGS.getOrdine());
        this.quantità = new SimpleStringProperty(orderGS.getQuantità());
    }

    public String getAzienda() {
        return azienda.get();
    }

    public StringProperty aziendaProperty() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda.set(azienda);
    }

    public String getOrdine() {
        return ordine.get();
    }

    public StringProperty ordineProperty() {
        return ordine;
    }

    public void setOrdine(String ordine) {
        this.ordine.set(ordine);
    }

    public String getQuantità() {
        return quantità.get();
    }

    public StringProperty quantitàProperty() {
        return quantità;
    }

    public void setQuantità(String quantità) {
        this.quantità.set(quantità);
    }
}

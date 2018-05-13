package getterAndSetter.people;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierGUI {

    private StringProperty azienda;
    private StringProperty partitaIva;
    private StringProperty fornitura;

    public SupplierGUI(SupplierGS supplierGS){

        this.azienda = new SimpleStringProperty(supplierGS.getAzienda());
        this.partitaIva = new SimpleStringProperty(supplierGS.getPartitaIva());
        this.fornitura = new SimpleStringProperty(supplierGS.getFornitura());
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

    public String getPartitaIva() {
        return partitaIva.get();
    }

    public StringProperty partitaIvaProperty() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva.set(partitaIva);
    }

    public String getFornitura() {
        return fornitura.get();
    }

    public StringProperty fornituraProperty() {
        return fornitura;
    }

    public void setFornitura(String fornitura) {
        this.fornitura.set(fornitura);
    }
}

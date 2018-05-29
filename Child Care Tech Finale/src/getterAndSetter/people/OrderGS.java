package getterAndSetter.people;

import java.io.Serializable;

public class OrderGS implements Serializable {

    private String azienda;
    private String ordine;
    private String quantità;

    public OrderGS(String azienda, String ordine, String quantità){

        this.azienda = azienda;
        this.ordine = ordine;
        this.quantità = quantità;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getOrdine() {
        return ordine;
    }

    public void setOrdine(String ordine) {
        this.ordine = ordine;
    }

    public String getQuantità() {
        return quantità;
    }

    public void setQuantità(String quantità) {
        this.quantità = quantità;
    }
}

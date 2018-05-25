package getterAndSetter.people;

import java.io.Serializable;

public class SupplierGS implements Serializable {

    private String nome;
    private String cognome;
    private String azienda;
    private String fornitura;
    private String partitaIva;

    public SupplierGS(String azienda, String fornitura, String partitaIva){

        this.azienda = azienda;
        this.fornitura = fornitura;
        this.partitaIva = partitaIva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getFornitura() {
        return fornitura;
    }

    public void setFornitura(String fornitura) {
        this.fornitura = fornitura;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

}

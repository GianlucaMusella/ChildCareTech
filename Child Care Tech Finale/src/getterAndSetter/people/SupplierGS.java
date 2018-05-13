package getterAndSetter.people;

import java.io.Serializable;

public class SupplierGS implements Serializable {

    private String nome;
    private String cognome;
    private String azienda;
    private String fornitura;
    private String partitaIva;

    public SupplierGS(String azienda, String partitaIva, String fornitura){

        this.azienda = azienda;
        this.partitaIva = partitaIva;
        this.fornitura = fornitura;
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

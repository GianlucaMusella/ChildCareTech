package DataEntry;

import java.util.Date;

public class Generality {
    private String nome;
    private String cognome;
    private Date datanascita;
    private String luogonascita;
    private String codicefiscale;
    private String sesso;

    public void setSesso (String sesso){
        this.sesso = sesso;
    }
    public void setNome (String nome) {
        this.nome = nome;
    }
    public void setCognome (String cognome) {
        this.cognome = cognome;
    }
    public void setLuogonascita (String luogonascita) {
        this.luogonascita = luogonascita;
    }
    public void setCodicefiscale (String codicefiscale){
        this.codicefiscale = codicefiscale;
    }
    public void setDatanascita (Date datanascita){
        this.datanascita = datanascita;
    }

    public Date getDatanascita() {
        return datanascita;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public String getLuogonascita() {
        return luogonascita;
    }

    public String getNome() {
        return nome;
    }

    public String getSesso() {
        return sesso;
    }
}

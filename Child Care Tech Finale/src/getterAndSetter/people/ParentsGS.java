package getterAndSetter.people;

import java.io.Serializable;
import java.util.Date;

public class ParentsGS implements Serializable {

    private int numeroDiTelefono;

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String luogoDiNascita;
    private Date data;
    private String sesso;

    public ParentsGS(String nome, String cognome, String codiceFiscale){

        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;

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

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public int getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(int number) {
        this.numeroDiTelefono = number;
    }

}

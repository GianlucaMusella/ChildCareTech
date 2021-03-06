package getterAndSetter.people;

import java.io.Serializable;

public class ContactGS implements Serializable{
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private int telefono;

    public ContactGS(String nome, String cognome, String codiceFiscale){

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
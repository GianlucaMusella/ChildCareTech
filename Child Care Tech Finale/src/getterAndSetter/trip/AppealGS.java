package getterAndSetter.trip;

import java.io.Serializable;

public class AppealGS implements Serializable {

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String presenza;
    private int pullman;


    public AppealGS(String nome, String cognome, String codiceFiscale, String presenza, int pullman){
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.presenza = presenza;
        this.pullman = pullman;
    }

    public int getPullman() {
        return pullman;
    }

    public void setPullman(int pullman) {
        this.pullman = pullman;
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

    public String getPresenza() {
        return presenza;
    }

    public void setPresenza(String presenza) {
        this.presenza = presenza;
    }

}

package getterAndSetter.people;

import java.io.Serializable;
import java.util.Date;

public class ChildGS implements Serializable{

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String luogoDiNascita;
    private Date data;
    private String allergie;
    private String codicefiscalegen1;
    private String codicefiscalegen2;
    private String codicefiscalecontatto;
    private String codicefiscalepediatra;
    private String idBambino;

    public ChildGS(String nome, String cognome, String codiceFiscale, String luogoDiNascita, Date data, String idBambino){

        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.luogoDiNascita = luogoDiNascita;
        this.data = data;
        this.idBambino = idBambino;

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

    public String getIdBambino() {
        return idBambino;
    }

    public void setIdBambino(String idBambino) {
        this.idBambino = idBambino;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public void setCodicefiscalegen1(String codicefiscalegen1) {
        this.codicefiscalegen1 = codicefiscalegen1;
    }

    public void setCodicefiscalegen2(String codicefiscalegen2){ this.codicefiscalegen2 = codicefiscalegen2;}

    public void setCodicefiscalecontatto (String codicefiscalecontatto) {this.codicefiscalecontatto = codicefiscalecontatto;}

    public void setIdbambino(String idbambino) {
        this.idBambino = idbambino;
    }

    public void setCodicefiscalepediatra(String codicefiscalepediatrapediatra) { this.codicefiscalepediatra = codicefiscalepediatrapediatra;    }

    public String getAllergie() {
        return allergie;
    }

    public String getCodicefiscalegen1() {
        return codicefiscalegen1;
    }

    public String getCodicefiscalegen2 () {return codicefiscalegen2;}

    public String getCodicefiscalecontatto () {return codicefiscalecontatto;}

    public String getIdbambino() {
        return idBambino;
    }

    public String getCodicefiscalepediatra () { return codicefiscalepediatra;
    }
}
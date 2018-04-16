package dataEntry;

import java.util.Date;

public class Child extends Generality{

    private String codiceFiscaleGenitore;       //richiedo questi codici fiscali perchè chiavi primarie nel db
    private String codiceFiscaleContatto;
    private String codiceFiscalePediatra;
    private int IDBambino;

    public void setIDBambino (int IDBambino) {
        this.IDBambino=IDBambino;
    }

    public int getIDBambino (){ return IDBambino; }

    public String getCodiceFiscaleGenitore() {
        return codiceFiscaleGenitore;
    }

    public void setCodiceFiscaleGenitore(String codiceFiscaleGenitore) {
        this.codiceFiscaleGenitore = codiceFiscaleGenitore;
    }

    public String getCodiceFiscaleContatto() {
        return codiceFiscaleContatto;
    }

    public void setCodiceFiscaleContatto(String codiceFiscaleContatto) {
        this.codiceFiscaleContatto = codiceFiscaleContatto;
    }

    public String getCodiceFiscalePediatra() {
        return codiceFiscalePediatra;
    }

    public void setCodiceFiscalePediatra(String codiceFiscalePediatra) {
        this.codiceFiscalePediatra = codiceFiscalePediatra;
    }
    public void insertChild (String nome, String cognome, String luogodiNascita, String sesso, String codiceFiscale, Date data, String codiceFiscaleGenitore, String codiceFiscaleContatto, String codiceFiscalePediatra){
        String SQL = "INSERT INTO Bambini (idBambino, CodiceFiscale, Nome, Cognome, Data di Nascita, Luogo di Nascita, Sesso) VALUES (" + IDBambino + "," + codiceFiscale + "," + nome + "," + cognome + "," + data + "," + luogodiNascita + "," + sesso + ";";
    }
}

package dataEntry;

import java.util.Date;

public class ChildGS extends Generality {
    private String allergie;
    private String codicefiscalegen1;
    private String codicefiscalegen2;
    private String codicefiscalecontatto;
    private String codicefiscalepediatra;
    private int idbambino;

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public void setCodicefiscalegen1(String codicefiscalegen1) {
        this.codicefiscalegen1 = codicefiscalegen1;
    }

    public void setCodicefiscalegen2(String codicefiscalegen2){ this.codicefiscalegen2 = codicefiscalegen2;}

    public void setCodicefiscalecontatto (String codicefiscalecontatto) {this.codicefiscalecontatto = codicefiscalecontatto;}

    public void setIdbambino(int idbambino) {
        this.idbambino = idbambino;
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

    public int getIdbambino() {
        return idbambino;
    }

    public String getCodicefiscalepediatra () { return codicefiscalepediatra;
    }
}
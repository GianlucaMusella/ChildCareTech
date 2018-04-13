package DataEntry;

public class Child extends Generality {
    private String allergie;
    private String codicefiscalegen;
    private int idpediatra;
    private int idbambino;

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public void setCodicefiscalegen(String codicefiscalegen) {
        this.codicefiscalegen = codicefiscalegen;
    }

    public void setIdbambino(int idbambino) {
        this.idbambino = idbambino;
    }

    public void setIdpediatra(int idpediatra) {
        this.idpediatra = idpediatra;
    }

    public String getAllergie() {
        return allergie;
    }

    public String getCodicefiscalegen() {
        return codicefiscalegen;
    }

    public int getIdbambino() {
        return idbambino;
    }

    public int getIdpediatra() {
        return idpediatra;
    }
}

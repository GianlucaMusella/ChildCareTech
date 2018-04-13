package DataEntry;

public class Employers extends Generality {
    private String mansione;
    private String allergie;

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public void setMansione (String mansione){
        this.mansione = mansione;
    }

    public String getMansione() {
        return mansione;
    }
}

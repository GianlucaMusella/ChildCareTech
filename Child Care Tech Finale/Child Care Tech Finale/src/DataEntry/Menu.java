package DataEntry;

public class Menu {
    private String primo;
    private String secondo;
    private String allergeni;
    private String primoalt;
    private String secondalt;
    private String allergenialt;

    public String getAllergeni() {
        return allergeni;
    }

    public String getPrimo() {
        return primo;
    }

    public String getSecondo() {
        return secondo;
    }

    public String getAllergenialt() {
        return allergenialt;
    }

    public String getPrimoalt() {
        return primoalt;
    }

    public String getSecondalt() {
        return secondalt;
    }

    public void setAllergeni(String allergeni) {
        this.allergeni = allergeni;
    }

    public void setPrimo(String primo) {
        this.primo = primo;
    }

    public void setSecondo(String secondo) {
        this.secondo = secondo;
    }

    public void setAllergenialt(String allergenialt) {
        this.allergenialt = allergenialt;
    }

    public void setPrimoalt(String primoalt) {
        this.primoalt = primoalt;
    }

    public void setSecondalt(String secondalt) {
        this.secondalt = secondalt;
    }
}

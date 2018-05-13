package getterAndSetter.food;

import java.io.Serializable;
import java.util.Date;

public class MenuGS implements Serializable{

    private String nomeMenu;
    private String nomePrimo;
    private String nomeSecondo;
    private Date giorno;

    public MenuGS(String nomeMenu, String nomePrimo, String nomeSecondo, Date giorno){

        this.nomeMenu = nomeMenu;
        this.nomePrimo = nomePrimo;
        this.nomeSecondo = nomeSecondo;
        this.giorno = giorno;
    }

    public String getNomeMenu() {
        return nomeMenu;
    }

    public void setNomeMenu(String nomeMenu) {
        this.nomeMenu = nomeMenu;
    }

    public String getNomePrimo() {
        return nomePrimo;
    }

    public void setNomePrimo(String nomePrimo) {
        this.nomePrimo = nomePrimo;
    }

    public String getNomeSecondo() {
        return nomeSecondo;
    }

    public void setNomeSecondo(String nomeSecondo) {
        this.nomeSecondo = nomeSecondo;
    }

    public Date getGiorno() {
        return giorno;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }
}

package trip;

import java.io.Serializable;

public class AppelloGS implements Serializable {

    private String nome;
    private String cognome;
    private String codicefiscale;
    private String presenza;

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getPresenza() {
        return presenza;
    }

    public AppelloGS(String nome, String cognome, String codicefiscale, String presenza){
        this.nome = nome;
        this.cognome = cognome;
        this.codicefiscale = codicefiscale;

        this.presenza = presenza;
    }

}

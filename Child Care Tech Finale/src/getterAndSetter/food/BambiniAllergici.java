package getterAndSetter.food;

import java.io.Serializable;

public class BambiniAllergici implements Serializable{
    // mydb.bambini.Nome, mydb.Bambini.Cognome, mydb.Allergeni.Nome, mydb.Primi.Nome, mydb.Secondi.Nome, mydb.menumensa.Nome
    private String nomeBambino;
    private String cognomeBambino;
    private String nomeAllergene;
    private String nomePrimo;
    private String nomeSecondo;
    private String nomeContorno;

    public BambiniAllergici (String nomeBambino, String cognomeBambino, String nomeAllergene, String nomePrimo, String nomeSecondo, String nomeContorno){
        this.nomeBambino = nomeBambino;
        this.cognomeBambino = cognomeBambino;
        this.nomeAllergene = nomeAllergene;
        this.nomePrimo = nomePrimo;
        this.nomeSecondo = nomeSecondo;
        this.nomeContorno = nomeContorno;
    }

    public String getNomeBambino() {
        return nomeBambino;
    }

    public void setNomeBambino(String nomeBambino) {
        this.nomeBambino = nomeBambino;
    }

    public String getCognomeBambino() {
        return cognomeBambino;
    }

    public void setCognomeBambino(String cognomeBambino) {
        this.cognomeBambino = cognomeBambino;
    }

    public String getNomeAllergene() {
        return nomeAllergene;
    }

    public void setNomeAllergene(String nomeAllergene) {
        this.nomeAllergene = nomeAllergene;
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

    public String getNomeContorno() {
        return nomeContorno;
    }

    public void setNomeContorno(String nomeContorno) {
        this.nomeContorno = nomeContorno;
    }
}

package dataEntry;

import java.util.Date;

public class Parents extends Generality{

    private int numeroDiTelefono;

    public Parents(String nome, String cognome, String codiceFiscale, String luogoDiNascita, Date data) {
        super(nome, cognome, codiceFiscale, luogoDiNascita, data);
    }

    public int getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(int number) {
        this.numeroDiTelefono = number;
    }

}

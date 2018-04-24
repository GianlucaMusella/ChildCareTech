package serverRMI;

import dataEntry.ChildGS;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface InterfaceRMI extends Remote {

    boolean login(String username, String password) throws Exception;
    boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception;
    boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra) throws Exception;
    boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception;
    boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception;
    ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception;
}

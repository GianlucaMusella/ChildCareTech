package serverRMI;

import dataEntry.ChildGS;
import dataEntry.Contact;
import dataEntry.Doctor;
import dataEntry.Parents;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface InterfaceRMI extends Remote {

    boolean login(String username, String password) throws Exception;
    boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception;
    boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String Contatto) throws Exception;
    boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception;
    boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception;
    ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception;
    boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception;

    boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception;

    ArrayList<ChildGS> viewChild() throws Exception;

    boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception;

    ArrayList<Parents> viewParents() throws Exception;

    ArrayList<Contact> viewContacts() throws Exception;

    ArrayList<Doctor> viewDoctors() throws Exception;


    void modifyChild(String CodicefiscaleOld, String CodicefiscaleNew, String Nome, String Cognome, String Luogo, String ID, java.util.Date data) throws Exception;
}
package serverRMI;

import getterAndSetter.people.*;
import getterAndSetter.food.AllergyPeopleGS;
import getterAndSetter.food.FirstDishGS;
import getterAndSetter.food.MenuGS;
import getterAndSetter.food.SecondDishGS;
import trip.AppelloGS;
import trip.TripGS;

import java.rmi.Remote;
import java.time.LocalDate;
import java.util.ArrayList;

public interface InterfaceRMI extends Remote {

    boolean login(String username, String password) throws Exception;

    boolean addSupplier(String name, String surname, String azienda, String fornitura, String partitaIva) throws Exception;
    ArrayList<SupplierGS> viewSupplier() throws Exception;
    ArrayList<SupplierGS> searchSupplier(String azienda, String fornitura, String partitaIva) throws Exception;
    void modifySupplier(String azienda, String nome, String cognome, String fornitura, String partitaIva) throws Exception;
    boolean addOrder(String azienda, String ordini, String quantità) throws Exception;
    boolean deleteSupplier(String azienda) throws Exception;

    boolean addChild(String codiceFiscale, String idBambino, String nome, String cognome, LocalDate data, String luogo, String allergie, String genitore1, String genitore2, String sesso, String pediatra, String Contatto) throws Exception;
    ArrayList<ChildGS> searchC(String name, String surname, String cod) throws Exception;
    ArrayList<ChildGS> viewChild() throws Exception;
    void modifyChild(String codiceFiscale, String Nome, String cognome, String luogo, LocalDate data, String idBambino) throws Exception;
    boolean deleteChild (String codiceFiscale) throws Exception;

    boolean addTeacher(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String insegnante, String username, String password) throws Exception;
    boolean addStaff(String nome, String cognome, String codiceFiscale, LocalDate data, String luogo, String allergie, String sesso, String mansione) throws Exception;
    ArrayList<StaffGS> viewStaff() throws Exception;
    ArrayList<StaffGS> searchStaff(String nome, String cognome, String cod) throws Exception;
    void modifyStaff(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String mansione) throws Exception;
    boolean deleteStaff(String codiceFiscale) throws Exception;

    boolean addParents(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String telefono, String sesso) throws Exception;
    ArrayList<ParentsGS> viewParents() throws Exception;
    ArrayList<ParentsGS> searchParents(String name, String cod) throws Exception;
    void modifyParents(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data, String telefono) throws Exception;
    boolean deleteParents(String codiceFiscale) throws Exception;

    boolean addContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception;
    ArrayList<ContactGS> viewContacts() throws Exception;
    ArrayList<ContactGS> searchContacts(String nome, String cod) throws Exception;
    void modifyContact(String codiceFiscale, String nome, String cognome, String telefono) throws Exception;
    boolean deleteContacts(String codiceFiscale) throws Exception;

    boolean addDoctor(String codiceFiscale, String nome, String cognome, LocalDate data, String luogo, String sesso) throws Exception;
    ArrayList<DoctorGS> viewDoctors() throws Exception;
    ArrayList<DoctorGS> searchDoctors(String name, String cod) throws Exception;
    void modifyDoctor(String codiceFiscale, String nome, String cognome, String luogo, LocalDate data) throws Exception;
    boolean deleteDoctors(String codiceFiscale) throws Exception;

    boolean addMenu(String nome, String primo, String secondo, LocalDate giorno) throws Exception;
    ArrayList<FirstDishGS> viewFirst() throws Exception;
    ArrayList<SecondDishGS> viewSecond() throws Exception;
    ArrayList<AllergyPeopleGS> viewAllergy() throws Exception;
    ArrayList<MenuGS> viewMenu() throws Exception;
    boolean addPrimo(String nome, String allergeni) throws Exception;
    boolean addSecondo(String nome, String allergeni) throws Exception;
    boolean deleteMenu(String nomeMenu) throws Exception;


    boolean newTrip(String id, String meta, LocalDate andata, LocalDate ritorno) throws Exception;
    ArrayList<TripGS> viewTrip() throws Exception;
    ArrayList<AppelloGS> loadDataServer(int idGita) throws Exception;
    void bambinoPresenteServer(String codiceFiscale, int idGita) throws Exception;
    void bambinoAssenteServer(String codiceFiscale, int idGita) throws Exception;
    boolean newpartecipanteTrip(String codiceFiscale, String idGita) throws Exception;
    boolean deleteTrip(String idGita) throws Exception;


}